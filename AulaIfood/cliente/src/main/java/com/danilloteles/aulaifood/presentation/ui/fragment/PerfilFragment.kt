package com.danilloteles.aulaifood.presentation.ui.fragment

import android.Manifest
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.danilloteles.aulaifood.databinding.FragmentPerfilBinding
import com.danilloteles.aulaifood.domain.model.UploadStorage
import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.aulaifood.presentation.ui.activity.LoginActivity
import com.danilloteles.aulaifood.presentation.viewmodel.AutenticacaoViewModel
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.exibirMensagem
import com.danilloteles.core.navegarPara
import com.danilloteles.core.util.ConstantesFirebase
import com.google.firebase.auth.FirebaseAuth
import com.permissionx.guolindev.PermissionX
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

@AndroidEntryPoint
class PerfilFragment : Fragment() {

   private lateinit var binding: FragmentPerfilBinding
   private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()
   private val alertaCarregamento by lazy {
      AlertaCarregamento( requireContext() )
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {

      binding = FragmentPerfilBinding.inflate(
         inflater,
         container,
         false
      )
      solicitarPermissoes()
      inicializarEventosClique()
      return binding.root
   }

   private val selecionarImagemPerfil = registerForActivityResult(
      ActivityResultContracts.PickVisualMedia()
   ) { uri ->
      if (uri != null) {
         binding.imagePerfil.setImageURI(uri)
         uploadImagemPerfil(uri)
      }
   }

   private fun uploadImagemPerfil(uri: Uri) {
      val idUsuario = autenticacaoViewModel.recuperarIdUsuarioLogado()
      autenticacaoViewModel.uploadImagem(
         UploadStorage(
            ConstantesFirebase.STORAGE_USUARIOS,
            idUsuario,
            uri
         )
      ) { uiStatus ->
         when (uiStatus) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               activity?.exibirMensagem(uiStatus.erro)
            }

            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               activity?.exibirMensagem("Imagem atualizada com sucesso")
            }

            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Fazendo upload da imagem")
            }
         }
      }
   }

   private fun inicializarEventosClique() {
      with( binding ){

         btnSalvarPerfil.setOnClickListener {
            atualizarDadosPerfilUsuario()
         }

         btnAlterarImagemPerfil.setOnClickListener {
            selecionarImagemPerfil.launch(
               PickVisualMediaRequest(
                  ActivityResultContracts.PickVisualMedia.ImageOnly
               )
            )
         }

         btnDeslogar.setOnClickListener {
            autenticacaoViewModel.deslogarUsuario()
            activity?.navegarPara( LoginActivity::class.java )
         }
      }
   }

   private fun atualizarDadosPerfilUsuario() {
      val nomePerfil = binding.editNomePerfil.text.toString()
      if ( nomePerfil.isNotEmpty() ) {
         autenticacaoViewModel.atualizarUsuario(
            Usuario( nome = nomePerfil )
         ){ uiStatus ->
            when( uiStatus ){
               is UIStatus.Sucesso -> {
                  alertaCarregamento.fechar()
                  activity?.exibirMensagem("Dados atualizados com sucesso!")
               }
               is UIStatus.Erro -> {
                  alertaCarregamento.fechar()
                  activity?.exibirMensagem( uiStatus.erro )
               }
               is UIStatus.Carregando -> {
                  alertaCarregamento.exibir("Atualizando dados")
               }
            }
         }
      }
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      exibirDadosUsuario()
   }

   private fun exibirDadosUsuario() {
      autenticacaoViewModel.recuperarDadosUsuarioLogado { uiStatus ->
         when (uiStatus) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               activity?.exibirMensagem( uiStatus.erro )
            }
            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               val usuario = uiStatus.dados
               exibirDados( usuario )
            }
            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Carregando dados do perfil")
            }
         }
      }
   }

   private fun exibirDados( usuario: Usuario ) {

      if ( usuario.urlPerfil.isNotEmpty() ) {
         Picasso.get()
            .load(usuario.urlPerfil)
            .into(binding.imagePerfil)
      }

      if ( usuario.nome.isNotEmpty() ) {
         binding.editNomePerfil.setText( usuario.nome )
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
               "As permissões são necessárias para configurar imagens para o perfil",
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
               activity?.exibirMensagem("Permissões negadas, não é possível configurar imagens de perfil")
            }
         }
   }

}