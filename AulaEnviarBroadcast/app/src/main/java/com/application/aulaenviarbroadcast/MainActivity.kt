package com.application.aulaenviarbroadcast

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.application.aulaenviarbroadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   
   private val binding by lazy {
       ActivityMainBinding.inflate( layoutInflater )
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)

      binding.btnAbrirPdf.setOnClickListener {
         val intent = Intent("com.application.aulaserviceandroid.ABRIR_ARQUIVO_PDF")
         intent.putExtra("ARQUIVO", "local/perfil.pdf") //Ação de Enviar o arquivo do PDF, com o nome do arquivo e o local aonde está o arquivo

         val componente = ComponentName(
            "com.application.aulaserviceandroid",
            "com.application.aulaserviceandroid.ComunicacaoBroadcastActivity"
         )
         intent.component = componente
         sendBroadcast( intent )

         //startActivity(Intent(this, MainActivity::class.java))
      }

   }
}