package com.danilloteles.aulaifood

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aulaifood.databinding.ActivityLoginBinding
import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.aulaifood.presentation.viewmodel.AutenticacaoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

   private val binding by lazy {
         ActivityLoginBinding.inflate( layoutInflater )
   }
   private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
   }

   private fun inicializar() {
      inicializarEventosClique()
      inicializarObservaveis()
   }

   private fun inicializarObservaveis() {
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
         btnLogin.setOnClickListener {
            val email = editLoginEmail.text.toString()
            val senha = editLoginSenha.text.toString()
            val usuario = Usuario(
               email, senha
            )
            autenticacaoViewModel.logarUsuario( usuario )
         }
      }
   }
}