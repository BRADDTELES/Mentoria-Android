package com.danilloteles.loja.presentation.ui.activity

import android.Manifest
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.esconderTeclado
import com.danilloteles.core.exibirMensagem
import com.danilloteles.core.navegarPara
import com.danilloteles.loja.databinding.ActivityCadastroProdutoBinding
import com.danilloteles.loja.domain.model.Produto
import com.danilloteles.loja.domain.model.UploadStorage
import com.danilloteles.loja.presentation.viewmodel.ProdutoViewModel
import com.danilloteles.loja.util.Constantes
import com.jamiltondamasceno.core.adicionarMascaraMoeda
import com.jamiltondamasceno.core.formatarComoMoeda
import com.jamiltondamasceno.core.moedaToDouble
import com.permissionx.guolindev.PermissionX
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import java.util.UUID

@AndroidEntryPoint
class CadastroProdutoActivity : AppCompatActivity() {

   private val binding by lazy {
      ActivityCadastroProdutoBinding.inflate( layoutInflater )
   }
   private val alertaCarregamento by lazy {
      AlertaCarregamento(this)
   }
   private val produtoViewModel: ProdutoViewModel by viewModels()
   private var idProduto = ""

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
      solicitarPermissoes()
   }

   override fun onStart() {
      super.onStart()
      recuperarDadosProduto()
   }

   private fun recuperarDadosProduto() {
      if ( idProduto.isNotEmpty() ) {
         produtoViewModel.recuperProdutoPeloId( idProduto ){ uiStatus ->
            when (uiStatus) {
               is UIStatus.Erro -> {
                  alertaCarregamento.fechar()
                  exibirMensagem(uiStatus.erro)
               }
               is UIStatus.Sucesso -> {
                  val produto = uiStatus.dados
                  exibirDadosProduto( produto )
               }
               is UIStatus.Carregando -> {
                  alertaCarregamento.exibir("Carregando dados do produto")
               }
            }
         }
      }
   }

   private fun exibirDadosProduto(produto: Produto) {

      if ( produto.url.isNotEmpty() ) {
         Picasso.get()
            .load(produto.url)
            .into(binding.imageCapaProduto)
      }
      if ( produto.nome.isNotEmpty() ) {
         binding.editNomeProduto.setText( produto.nome )
      }
      if ( produto.descricao.isNotEmpty() ) {
         binding.editDescricaoProduto.setText( produto.descricao )
      }
      if ( produto.preco >= 0.0 ) {
         binding.editPrecoProduto.setText( produto.preco.formatarComoMoeda() )
      }
      if ( produto.emDestaque == true ) {
         binding.switchProdutoEmDestaque.isChecked = true
         binding.tlPrecoDestaque.visibility = View.VISIBLE
         if ( produto.precoDestaque >= 0.0 ) {
            binding.editPrecoDestaqueProduto.setText( produto.precoDestaque.formatarComoMoeda() )
         }
      }else{
         binding.switchProdutoEmDestaque.isChecked = false
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

      val bundle = intent.extras
      idProduto = bundle?.getString("idProduto") ?: ""

      inicializarEventosClique()
      inicializarMascaraMoeda()
   }

   private fun inicializarMascaraMoeda() {

      binding.editPrecoProduto.adicionarMascaraMoeda(
         local = Locale("pt", "BR"),
         maximoDigitosDecimais = 2,
         simboloMoedaCustomizado = "R$",
         maximoDigitos = 7
      )

      binding.editPrecoDestaqueProduto.adicionarMascaraMoeda(
         local = Locale("pt", "BR"),
         maximoDigitosDecimais = 2,
         simboloMoedaCustomizado = "R$",
         maximoDigitos = 7
      )

   }



   private fun uploadImagemProduto(uri: Uri) {
      produtoViewModel.uploadImagem(
         UploadStorage(
            Constantes.STORAGE_PRODUTOS,
            UUID.randomUUID().toString(),
            uri
         ), idProduto
      ) { uiStatus ->
         when (uiStatus) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               exibirMensagem(uiStatus.erro)
            }

            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               idProduto = uiStatus.dados
               exibirMensagem("Imagem do produto atualizada com sucesso")
            }

            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Fazendo upload da imagem do produto")
            }
         }
      }
   }

   private val selecionarImagemProduto = registerForActivityResult(
      ActivityResultContracts.PickVisualMedia()
   ) { uri ->
      if (uri != null) {
         binding.imageCapaProduto.setImageURI(uri)
         uploadImagemProduto(uri)
      } else {
         exibirMensagem("Nenhuma imagem foi selecionada para o produto!")
      }
   }

   private fun inicializarEventosClique() {
      with(binding){
         btnCadastroProdutoVoltar.setOnClickListener {
            navegarPara(CardapioActivity::class.java)
         }

         btnSelecionarImagemProduto.setOnClickListener {
            selecionarImagemProduto.launch(
               PickVisualMediaRequest(
                  ActivityResultContracts.PickVisualMedia.ImageOnly
               )
            )
         }

         //Esconder preço de destaque
         switchProdutoEmDestaque.setOnClickListener {
            if (switchProdutoEmDestaque.isChecked) {
               tlPrecoDestaque.visibility = View.VISIBLE
               editPrecoDestaqueProduto.setText("")
            }else{
               tlPrecoDestaque.visibility = View.GONE
               editPrecoDestaqueProduto.setText("")
            }
         }

         //Cadastrar o produto
         btnSalvarProduto.setOnClickListener { view ->
            view.esconderTeclado()

            //Remover Focus
            editNomeProduto.clearFocus()
            editDescricaoProduto.clearFocus()
            editPrecoProduto.clearFocus()
            editPrecoDestaqueProduto.clearFocus()

            val nome = editNomeProduto.text.toString()
            val descricao = editDescricaoProduto.text.toString()
            val preco = editPrecoProduto.moedaToDouble()
            val precoDestaque = editPrecoDestaqueProduto.moedaToDouble()
            val emDestaque = switchProdutoEmDestaque.isChecked

            val produto = Produto(
               id = idProduto, nome = nome, descricao = descricao,
               preco = preco, precoDestaque = precoDestaque, emDestaque = emDestaque
            )

            val retornoValidacao = validarCampos( produto )
            if ( retornoValidacao ) {
               cadastrarProduto( produto )
            }else{
               exibirMensagem("Prencha os campos do produto")
            }
         }
      }
   }

   private fun cadastrarProduto(produto: Produto) {
      produtoViewModel.salvar( produto ){ uiStatus ->
         when (uiStatus) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               exibirMensagem(uiStatus.erro)
            }

            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               idProduto = uiStatus.dados
               exibirMensagem("Produto atualizado com sucesso!")
            }

            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Atualizando dados do produto")
            }
         }
      }

   }

   private fun validarCampos(produto: Produto) : Boolean {

      if ( produto.nome.isEmpty() ) return false
      if ( produto.descricao.isEmpty() ) return false
      if ( produto.preco <= 0.0 ) return false
      if ( produto.emDestaque == true ){
         if ( produto.precoDestaque <= 0.0 ) return false
      }
      return true
   }
}