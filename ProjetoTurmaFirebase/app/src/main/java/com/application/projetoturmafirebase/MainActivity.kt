package com.application.projetoturmafirebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.projetoturmafirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        with(binding){
            btnDeslogar.setOnClickListener {
                autenticacao.signOut()
                startActivity(
                    Intent(applicationContext, LoginActivity::class.java) //Intent não funciona em uma função de escopo, para isso precisa trocar o this = applicationContext
                )
            }
        }

    }
}