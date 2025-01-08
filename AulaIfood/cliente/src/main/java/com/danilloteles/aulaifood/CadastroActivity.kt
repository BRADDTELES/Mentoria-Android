package com.danilloteles.aulaifood

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aulaifood.databinding.ActivityCadastroBinding

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