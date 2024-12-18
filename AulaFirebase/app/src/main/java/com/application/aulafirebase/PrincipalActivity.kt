package com.application.aulafirebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulafirebase.databinding.ActivityPrincipalBinding
import com.google.firebase.auth.FirebaseAuth

class PrincipalActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPrincipalBinding.inflate(layoutInflater)
    }

    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        binding.btnDeslogar.setOnClickListener {
            deslogarUsuario()
        }
    }

    private fun deslogarUsuario() {
        autenticacao.signOut()
        // Encaminhar usuário para Tela de Login (MainActivity), após ter clicado em Deslogar
        startActivity(
            Intent(this, MainActivity::class.java)
        )
    }
}