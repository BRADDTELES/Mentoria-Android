package com.application.aulatesteexpresso

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulatesteexpresso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private val binding by lazy {
       ActivityMainBinding.inflate( layoutInflater )
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )

      val bundle = intent.extras
      if (bundle != null){
         val email = bundle.getString("email")
         binding.textEmail.text = email
      }

      binding.btnVoltar.setOnClickListener {
         finish()  // Finaliza a activity atual e volta para a anterior
      }

   }
}