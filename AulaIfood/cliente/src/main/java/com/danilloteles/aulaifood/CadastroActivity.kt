package com.danilloteles.aulaifood

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aulaifood.databinding.ActivityCadastroBinding
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class CadastroActivity : AppCompatActivity() {

   private val binding by lazy {
         ActivityCadastroBinding.inflate( layoutInflater )
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
   }

   private fun inicializar() {
      inicializarToolbar()
      inicializarEventosClique()
   }

   private fun inicializarEventosClique() {
      with( binding ){
         btnCadastrar.setOnClickListener {

            val nome = editCadastroNome.text.toString()
            val email = editCadastroEmail.text.toString()
            val senha = editCadastroSenha.text.toString()
            val telefone = editCadastroTelefone.text.toString()

            val valNome = nome.validator()
               .minLength(6)
               .check()

            val valEmail = email.validator()
               .validEmail()
               .check()

            val valSenha = senha.validator()
               .minLength(6)
               .check()

            val valTelefone = telefone.validator()
               .minLength(10)
               .check()

            Log.i("validacao", "nome($valNome) email($valEmail) senha($valSenha) telefone($valTelefone)")
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