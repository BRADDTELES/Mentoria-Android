package com.danilloteles.loja.presentation.ui.activity

import android.Manifest
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.esconderTeclado
import com.danilloteles.core.exibirMensagem
import com.danilloteles.core.navegarPara
import com.danilloteles.loja.databinding.ActivityLojaBinding
import com.danilloteles.loja.domain.model.Categoria
import com.danilloteles.loja.domain.model.Loja
import com.danilloteles.loja.domain.model.UploadLoja
import com.danilloteles.loja.presentation.viewmodel.LojaViewModel
import com.danilloteles.loja.util.Constantes
import com.permissionx.guolindev.PermissionX
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LojaActivity : AppCompatActivity() {

   private val binding by lazy {
      ActivityLojaBinding.inflate(layoutInflater)
   }
   private val alertaCarregamento by lazy {
      AlertaCarregamento(this)
   }
   private val lojaViewModel: LojaViewModel by viewModels()
   private lateinit var spinnerAdapter: ArrayAdapter<String>
   private lateinit var listaCategorias: List<Categoria>

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)
      inicializar()
      solicitarPermissoes()
   }

   override fun onStart() {
      super.onStart()
      carregarDados()
   }

   private fun carregarDados() {

      lojaViewModel.recuperarDadosLoja{ uiStatus ->
         when (uiStatus) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               exibirMensagem(uiStatus.erro)
            }

            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               val loja = uiStatus.dados
               val idCategoria = loja.idCategoria
               exibirDadosLoja( loja )
               carregarCategorias( idCategoria )
            }

            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Carregando dados da loja")
            }
         }
      }

   }

   private fun carregarCategorias(idCategoria: String) {
      lojaViewModel.recuperarCategorias { uiStatus ->
         when ( uiStatus ) {
            is UIStatus.Erro -> {
               exibirMensagem( uiStatus.erro )
            }
            is UIStatus.Sucesso -> {

               listaCategorias = uiStatus.dados
               val listaSpinnerCategorias = mutableListOf("Selecione uma categoria")
               listaSpinnerCategorias.addAll(
                  listaCategorias.map { categoria -> categoria.nome }
               )
               spinnerAdapter.clear()
               spinnerAdapter.addAll( listaSpinnerCategorias )

               //Marcação da categorias selecionada
               val posicaoSpinner = listaCategorias.indexOfFirst { categoria ->
                  categoria.id == idCategoria
               }
               binding.spinnerCategoriaLoja.setSelection( posicaoSpinner +1 )

            }
            is UIStatus.Carregando -> {
               spinnerAdapter.clear()
               spinnerAdapter.addAll( mutableListOf("Carregando"))
            }
         }
      }
   }

   private fun exibirDadosLoja( loja: Loja) {

      if ( loja.urlCapa.isNotEmpty() ) {
         Picasso.get()
            .load(loja.urlCapa)
            .into(binding.imageCapaLoja)
      }
      if ( loja.urlPerfil.isNotEmpty() ) {
         Picasso.get()
            .load(loja.urlPerfil)
            .into(binding.imagePerfilLoja)
      }
      if ( loja.nome.isNotEmpty() ) {
         binding.editNomeLoja.setText( loja.nome )
      }
      if ( loja.razaoSocial.isNotEmpty() ) {
         binding.editRazaoSocialLoja.setText( loja.razaoSocial )
      }
      if ( loja.cnpj.isNotEmpty() ) {
         binding.editCnpjLoja.setText( loja.cnpj )
      }
      if ( loja.sobreEmpresa.isNotEmpty() ) {
         binding.editSobreLoja.setText( loja.sobreEmpresa )
      }
      if ( loja.telefone.isNotEmpty() ) {
         binding.editTelefoneLoja.setText( loja.telefone )
      }

   }

   private fun solicitarPermissoes() {
      val listaPermissoes = mutableListOf<String>()

      //Solicitar permissões de acordo com versões
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
         listaPermissoes.add(Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED) //>=34
         listaPermissoes.add(READ_MEDIA_IMAGES) //>=33
      } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
         listaPermissoes.add(READ_MEDIA_IMAGES) //>=33
      } else {
         listaPermissoes.add(Manifest.permission.READ_EXTERNAL_STORAGE) //<=32
      }
      PermissionX.init(this)
         .permissions(listaPermissoes)
         .explainReasonBeforeRequest()
         .onExplainRequestReason { scope, permissoesNegadas ->
            scope.showRequestReasonDialog(
               permissoesNegadas,
               "As permissões são necessárias para configurar imagens para a loja",
               "Conceder permissões",
               "Cancelar"
            )
         }
         .onForwardToSettings { scope, permissoesNegadas ->
            scope.showForwardToSettingsDialog(
               permissoesNegadas,
               "Voçê precisa conceder permissões manualmente em configurações",
               "Abrir configurações",
               "Cancelar"
            )
         }
         .request { todasConcedidas, permissoesConcedidas, permissoesNegadas ->
            if (!todasConcedidas) {
               exibirMensagem("Permissões negadas, não é possível configurar imagens de perfil e capa")
            }
         }
   }

   private fun inicializar() {
      inicializarSpinnerCategorias()
      inicializarEventosClique()
      inicializarObservaveis()
   }

   private fun inicializarSpinnerCategorias() {

      spinnerAdapter = ArrayAdapter(
         this,
         android.R.layout.simple_spinner_dropdown_item,
         mutableListOf()
      )
      binding.spinnerCategoriaLoja.adapter = spinnerAdapter

   }

   private val selecionarImagemPerfil = registerForActivityResult(
      ActivityResultContracts.PickVisualMedia()
   ) { uri ->
      if (uri != null) {
         binding.imagePerfilLoja.setImageURI(uri)
         //Upload da imagem no Firebase (enviar imagens de capa e perfil para o Firebase)
         uploudImagemPerfil(uri)
      } else {
         exibirMensagem("Nenhuma imagem foi selecionada para perfil!")
      }
   }

   private fun uploudImagemPerfil(uri: Uri) {
      lojaViewModel.uploadImagem(
         UploadLoja(
            Constantes.STORAGE_LOJAS,
            "imagem_perfil",
            uri
         )
      ) { uiStatus ->
         when (uiStatus) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               exibirMensagem(uiStatus.erro)
            }

            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               exibirMensagem("Imagem de perfil atualizada com sucesso")
            }

            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Fazendo upload da imagem de perfil")
            }
         }
      }
   }

   private val selecionarImagemCapa = registerForActivityResult(
      ActivityResultContracts.PickVisualMedia()
   ) { uri ->
      if (uri != null) {
         binding.imageCapaLoja.setImageURI(uri)
         //Upload da imagem no Firebase (enviar imagens de capa e perfil para o Firebase)
         uploudImagemCapa(uri)
      } else {
         exibirMensagem("Nenhuma imagem foi selecionada para capa!")
      }
   }

   private fun uploudImagemCapa(uri: Uri) {
      lojaViewModel.uploadImagem(
         UploadLoja(
            Constantes.STORAGE_LOJAS,
            "imagem_capa",
            uri
         )
      ) { uiStatus ->
         when (uiStatus) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               exibirMensagem(uiStatus.erro)
            }

            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               exibirMensagem("Imagem de capa atualizada com sucesso")
            }

            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Fazendo upload da imagem de capa")
            }
         }
      }
   }

   private fun inicializarEventosClique() {
      with(binding) {
         btnLojaVoltar.setOnClickListener {
            navegarPara(HomeActivity::class.java)
         }
         btnSelecionarImagemCapa.setOnClickListener {
            selecionarImagemCapa.launch(
               PickVisualMediaRequest(
                  ActivityResultContracts.PickVisualMedia.ImageOnly
               )
            )
         }
         btnSelecionarImagemPerfil.setOnClickListener {
            selecionarImagemPerfil.launch(
               PickVisualMediaRequest(
                  ActivityResultContracts.PickVisualMedia.ImageOnly
               )
            )
         }
         btnAtualizar.setOnClickListener { view ->
            view.esconderTeclado()

            //Remover Focus
            editNomeLoja.clearFocus()
            editRazaoSocialLoja.clearFocus()
            editCnpjLoja.clearFocus()
            editSobreLoja.clearFocus()
            editTelefoneLoja.clearFocus()

            val nome = editNomeLoja.text.toString()
            val razaoSocial = editRazaoSocialLoja.text.toString()
            val cnpj = editCnpjLoja.text.toString()
            val sobre = editSobreLoja.text.toString()
            val telefone = editTelefoneLoja.text.toString()

            var idCategoria = ""
            var categoria = ""
            val posicaoSelecionada = spinnerCategoriaLoja.selectedItemPosition
            if ( posicaoSelecionada > 0 ) {
               val categoriaSelecionada = listaCategorias[ posicaoSelecionada -1 ]
               idCategoria = categoriaSelecionada.id
               categoria = categoriaSelecionada.nome
            }

            val loja = Loja(
               "",
               idCategoria,
               categoria,
               nome,
               razaoSocial,
               cnpj,
               sobre,
               telefone
            )

            atualizarLoja( loja )
         }
      }
   }

   private fun atualizarLoja( loja: Loja ) {
      lojaViewModel.atualizarLoja(loja){ uiStatus ->
         when (uiStatus) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               exibirMensagem(uiStatus.erro)
            }

            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               exibirMensagem("Dados atualizados com sucesso")
               //navegarPara(HomeActivity::class.java)
            }

            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Atualizando dados da loja")
            }
         }
      }
   }

   private fun inicializarObservaveis() {

   }

}