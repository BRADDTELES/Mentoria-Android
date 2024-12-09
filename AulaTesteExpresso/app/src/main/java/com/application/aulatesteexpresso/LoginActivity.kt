package com.application.aulatesteexpresso

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.application.aulatesteexpresso.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

   private val binding by lazy {
       ActivityLoginBinding.inflate( layoutInflater )
   }
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      
      binding.btnLogar.setOnClickListener {
         //Toast.makeText(this, "Botão clicado", Toast.LENGTH_SHORT).show()

         val email = binding.editEmail.text.toString()
         val intent = Intent(this, MainActivity::class.java)
         intent.putExtra("email", email)
         startActivity(intent)

      }
      
   }
}