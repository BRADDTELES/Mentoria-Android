package com.danilloteles.aulaifood

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aulaifood.databinding.ActivityCadastroBinding
import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.aulaifood.presentation.viewmodel.AutenticacaoViewModel
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroActivity : AppCompatActivity() {

   private val binding by lazy {
         ActivityCadastroBinding.inflate( layoutInflater )
   }
   private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
   }

   private fun inicializar() {
      inicializarToolbar()
      inicializarEventosClique()
      inicializarObservaveis()
   }

   private fun inicializarObservaveis() {

      autenticacaoViewModel.resultadoValidacao
         .observe(this){ resultadoValidacao ->

            with( binding ){

               editCadastroNome.error =
                  if (resultadoValidacao.nome) null else getString(R.string.erro_cadastro_nome)

               editCadastroEmail.error =
                  if (resultadoValidacao.email) null else getString(R.string.erro_cadastro_email)

               editCadastroSenha.error =
                  if (resultadoValidacao.senha) null else getString(R.string.erro_cadastro_senha)

               editCadastroTelefone.error =
                  if (resultadoValidacao.telefone) null else getString(R.string.erro_cadastro_telefone)

            }

      }

   }

   private fun inicializarEventosClique() {
      with( binding ){
         btnCadastrar.setOnClickListener {

            val nome = editCadastroNome.text.toString()
            val email = editCadastroEmail.text.toString()
            val senha = editCadastroSenha.text.toString()
            val telefone = editCadastroTelefone.text.toString()

            val usuario = Usuario(
               email, senha, nome, telefone
            )
            autenticacaoViewModel.cadastrarUsuario( usuario )

         }
      }
   }

   private fun inicializarToolbar() {
      val toolbar = binding.includeTbPrincipal.tbPrincipal
      setSupportActionBar( toolbar )

      supportActionBar?.apply {
         title = "Cadastro de usuário"
         setDisplayHomeAsUpEnabled(true)
      }
   }
}