package com.danilloteles.loja.presentation.ui.activity

import android.Manifest
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.exibirMensagem
import com.danilloteles.core.navegarPara
import com.danilloteles.loja.R
import com.danilloteles.loja.databinding.ActivityCadastroBinding
import com.danilloteles.loja.databinding.ActivityLoginBinding
import com.danilloteles.loja.databinding.ActivityLojaBinding
import com.danilloteles.loja.domain.model.UploadLoja
import com.danilloteles.loja.presentation.viewmodel.AutenticacaoViewModel
import com.danilloteles.loja.presentation.viewmodel.LojaViewModel
import com.danilloteles.loja.util.Constantes
import com.permissionx.guolindev.PermissionX
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

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)
      inicializar()
      solicitarPermissoes()
   }

   private fun solicitarPermissoes() {
      val listaPermissoes = mutableListOf<String>()

      //Solicitar permissões de acordo com versões
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
         listaPermissoes.add(Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED)//>=34
         listaPermissoes.add(READ_MEDIA_IMAGES)//>=33
      }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
         listaPermissoes.add(READ_MEDIA_IMAGES)//>=33
      }else{
         listaPermissoes.add(Manifest.permission.READ_EXTERNAL_STORAGE)//<=32
      }
      PermissionX.init(this)
         .permissions( listaPermissoes )
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
         .request{ todasConcedidas, permissoesConcedidas, permissoesNegadas ->
            if ( !todasConcedidas ) {
               exibirMensagem("Permissões negadas, não é possível configurar imagens de perfil e capa")
            }
         }
   }

   private fun inicializar() {
      inicializarEventosClique()
      inicializarObservaveis()
   }

   private fun uploudImagemPerfil(uri: Uri) {

      lojaViewModel.uploadImagem(
         UploadLoja(
            Constantes.STORAGE_LOJAS,
            "imagem_perfil",
            uri
         )
      ){ uiStatus ->
         when( uiStatus ){
            is UIStatus.Erro -> {
               exibirMensagem( uiStatus.erro )
            }
            is UIStatus.Sucesso -> {
               exibirMensagem("Imagem de perfil atualizada com sucesso")
            }
         }
      }

   }

   private fun uploudCapaPerfil(uri: Uri) {

      lojaViewModel.uploadImagem(
         UploadLoja(
            Constantes.STORAGE_LOJAS,
            "imagem_capa",
            uri
         )
      ){ uiStatus ->
         when( uiStatus ){
            is UIStatus.Erro -> {
               exibirMensagem( uiStatus.erro )
            }
            is UIStatus.Sucesso -> {
               exibirMensagem("Imagem de capa atualizada com sucesso")
            }
         }
      }

   }

   private val selecionarImagemPerfil = registerForActivityResult(
      ActivityResultContracts.PickVisualMedia()
   ){ uri ->
      if (uri != null) {
         binding.imagePerfilLoja.setImageURI( uri)
         //Upload da imagem no Firebase (enviar imagens de capa e perfil para o Firebase)
         uploudImagemPerfil( uri )
      } else {
         exibirMensagem("Nenhuma imagem foi selecionada para perfil!")
      }
   }

   private val selecionarImagemCapa = registerForActivityResult(
      ActivityResultContracts.PickVisualMedia()
   ){ uri ->
      if (uri != null) {
         binding.imageCapaLoja.setImageURI( uri)
         //Upload da imagem no Firebase (enviar imagens de capa e perfil para o Firebase)
         uploudCapaPerfil( uri )
      } else {
         exibirMensagem("Nenhuma imagem foi selecionada para capa!")
      }
   }

   private fun inicializarEventosClique() {
      with(binding){
         btnLojaVoltar.setOnClickListener{
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
         btnAtualizar.setOnClickListener {  }
      }
   }

   private fun inicializarObservaveis() {

   }
}