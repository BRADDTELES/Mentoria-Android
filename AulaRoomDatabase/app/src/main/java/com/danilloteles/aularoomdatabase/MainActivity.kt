package com.danilloteles.aularoomdatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aularoomdatabase.data.BandoDados
import com.danilloteles.aularoomdatabase.data.dao.UsuarioDAO
import com.danilloteles.aularoomdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var bancoDados: BandoDados
    private lateinit var usuarioDAO: UsuarioDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        bancoDados = BandoDados.recuperarInstanciaRoom( this )
        usuarioDAO = bancoDados.recuperarUsuarioDao()

        binding.btnSalvar.setOnClickListener {  }
        binding.btnRemover.setOnClickListener {  }
        binding.btnAtualizar.setOnClickListener {  }
        binding.btnListar.setOnClickListener {  }

    }
}