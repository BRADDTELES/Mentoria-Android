package com.danilloteles.loja.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.esconderTeclado
import com.danilloteles.core.exibirMensagem
import com.danilloteles.core.navegarPara
import com.danilloteles.loja.R
import com.danilloteles.loja.databinding.ActivityLoginBinding
import com.danilloteles.loja.presentation.viewmodel.AutenticacaoViewModel
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

   override fun onStart() {
      super.onStart()
      val usuarioLogado = autenticacaoViewModel.verificarUsuarioLogado()
      if ( usuarioLogado ) {
         navegarPara( HomeActivity::class.java )
      }
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
      FirebaseAuth.getInstance().signOut()
   }

   private fun inicializar() {
      inicializarEventosClique()
      inicializarObservaveis()
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
                     navegarPara( HomeActivity::class.java )
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