package com.application.aulaserviceandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulaserviceandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private val binding by lazy {
       ActivityMainBinding.inflate( layoutInflater )
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)

      val meuServico = Intent(this, MeuServico::class.java)
      binding.btnIniciarService.setOnClickListener {
         startService( meuServico )
      }
   }
}