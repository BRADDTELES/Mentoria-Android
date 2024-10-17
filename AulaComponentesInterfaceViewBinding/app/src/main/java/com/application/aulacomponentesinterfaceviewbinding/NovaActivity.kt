package com.application.aulacomponentesinterfaceviewbinding

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.application.aulacomponentesinterfaceviewbinding.databinding.ActivityNovaBinding
import com.application.aulacomponentesinterfaceviewbinding.databinding.ActivityToolbarActionbarBinding

class NovaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNovaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        inicializarToolbar()
    }

    private fun inicializarToolbar() {

        binding.includeToolbar.clLogo.visibility = View.GONE
        binding.includeToolbar.tbPrincipal.title = "Upload Vídeo"
        setSupportActionBar( binding.includeToolbar.tbPrincipal )

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // habilita o botão de voltar
    }
}