package com.danilloteles.loja.presentation.ui.activity

import android.Manifest
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.exibirMensagem
import com.danilloteles.core.navegarPara
import com.danilloteles.loja.R
import com.danilloteles.loja.databinding.ActivityCadastroBinding
import com.danilloteles.loja.databinding.ActivityLoginBinding
import com.danilloteles.loja.databinding.ActivityLojaBinding
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

   private fun inicializarEventosClique() {
      with(binding){
         btnLojaVoltar.setOnClickListener{
            navegarPara(HomeActivity::class.java)
         }
         btnSelecionarImagemCapa.setOnClickListener {  }
         btnSelecionarImagemPerfil.setOnClickListener {  }
         btnAtualizar.setOnClickListener {  }
      }
   }

   private fun inicializarObservaveis() {

   }
}