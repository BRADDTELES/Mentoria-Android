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

      //val meuServico = Intent(this, MeuServico::class.java)
      val minhaConexaoServico = Intent(this, MinhaConexao::class.java)
      binding.btnIniciarService.setOnClickListener {
         /*meuServico.putExtra("tempoDuracao",3000L)
         startService( meuServico )*/
         minhaConexaoServico.putExtra("tempoDuracao",3000L)
         startService( minhaConexaoServico )
      }

      binding.btnPararService.setOnClickListener {
         /*stopService( meuServico )*/
         stopService( minhaConexaoServico )
      }

   }
}