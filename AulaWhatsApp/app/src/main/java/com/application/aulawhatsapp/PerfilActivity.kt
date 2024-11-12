package com.application.aulawhatsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulawhatsapp.databinding.ActivityMainBinding
import com.application.aulawhatsapp.databinding.ActivityPerfilBinding
import com.google.firebase.auth.FirebaseAuth

class PerfilActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPerfilBinding.inflate(layoutInflater)
    }
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializarToolbar()

    }

    private fun inicializarToolbar() {
        val toolbar = binding.includeToolbarPerfil.tbPrincipal
        setSupportActionBar( toolbar )
        supportActionBar?.apply {
            title = "Editar perfil"
            setDisplayHomeAsUpEnabled(true)
        }
    }
}