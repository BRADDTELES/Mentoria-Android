package com.danilloteles.aulaifood.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.danilloteles.aulaifood.R
import com.danilloteles.aulaifood.databinding.ActivityLoginBinding
import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.aulaifood.presentation.viewmodel.AutenticacaoViewModel
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.esconderTeclado
import com.danilloteles.core.exibirMensagem
import com.danilloteles.core.navegarPara
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

   private val binding by lazy {
         ActivityLoginBinding.inflate( layoutInflater )
   }
   private val alertaCarregamento by lazy {
      AlertaCarregamento(this)
   }
   private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()

   override fun onCreate(savedInstanceState: Bundle?) {

      val splashScreen = installSplashScreen()
      //Thread.sleep(3000)
      splashScreen.setKeepOnScreenCondition{
         val usuarioLogado = autenticacaoViewModel.verificarUsuarioLogado()
         if ( usuarioLogado ) {
            navegarPara( MainActivity::class.java )
         }
         false
      }

      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
      FirebaseAuth.getInstance().signOut()
   }

   private fun inicializar() {
      inicializarEventosClique()
      inicializarObservaveis()
   }

   override fun onStart() {
      super.onStart()
      autenticacaoViewModel.verificarUsuarioLogado()
   }

   fun navegarTelaPrincipal() {
      startActivity(
         Intent(this, MainActivity::class.java)
      )
   }

   private fun inicializarObservaveis() {

      autenticacaoViewModel.carregando.observe(this){ carregando ->
         if ( carregando ) {
            alertaCarregamento.exibir("Efetuando login!")
         }else{
            alertaCarregamento.fechar()
         }
      }

      autenticacaoViewModel.resultadoValidacao
         .observe(this){ resultadoValidacao ->
            with( binding ){

               editLoginEmail.error =
                  if (resultadoValidacao.email) null else getString(R.string.erro_cadastro_email)

               editLoginSenha.error =
                  if (resultadoValidacao.senha) null else getString(R.string.erro_cadastro_senha)

            }
         }
   }

   private fun inicializarEventosClique() {
      with( binding ) {
         textCadastro.setOnClickListener {
            startActivity(
               Intent(applicationContext, CadastroActivity::class.java)
            )
         }
         btnLogin.setOnClickListener { view ->

            //Esconder o Teclado
            view.esconderTeclado()

            //Remover Focus
            editLoginEmail.clearFocus()
            editLoginSenha.clearFocus()

            val email = editLoginEmail.text.toString()
            val senha = editLoginSenha.text.toString()
            val usuario = Usuario(
               email, senha
            )
            autenticacaoViewModel.logarUsuario( usuario ){ uiStatus ->
               when( uiStatus ){
                  is UIStatus.Sucesso -> {
                     navegarPara( MainActivity::class.java )
                  }
                  is UIStatus.Erro -> {
                     exibirMensagem( uiStatus.erro )
                  }
               }
            }
         }
      }
   }
}