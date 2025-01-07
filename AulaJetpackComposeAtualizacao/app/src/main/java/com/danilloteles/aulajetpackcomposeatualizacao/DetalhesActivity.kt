package com.danilloteles.aulajetpackcomposeatualizacao

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aulajetpackcomposeatualizacao.databinding.ActivityDetalhesBinding

class DetalhesActivity : AppCompatActivity() {

   private val binding by lazy {
         ActivityDetalhesBinding.inflate( layoutInflater )
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )

      binding.btnVoltar.setOnClickListener {
         finish()
      }

   }
}