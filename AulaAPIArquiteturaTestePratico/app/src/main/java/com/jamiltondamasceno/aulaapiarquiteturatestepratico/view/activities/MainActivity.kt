package com.jamiltondamasceno.aulaapiarquiteturatestepratico.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.api.RetrofitCustom
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.repository.UsuarioRepositoryImpl
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.domain.UsuarioUseCase
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.view.viewmodel.UsuarioViewModel
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.view.viewmodel.UsuarioViewModelFactory
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
        inicializar()
    }

    override fun onStart() {
        super.onStart()
        usuarioViewModel.recuperarListaUsuarios()
    }

    private fun inicializar() {
        inicializarViews()
        inicializarObservables()
    }

    private fun inicializarObservables() {

        usuarioViewModel.listaUsuarios.observe(this){ listaUsuarios ->

            var dadosUsuarios = ""
            listaUsuarios.forEach { usuario ->
                dadosUsuarios += "+ ${usuario.firstName} (${usuario.age}) \n"
                dadosUsuarios += "----------------\n"
            }
            binding.textLista.text = dadosUsuarios

        }

    }

    private fun inicializarViews() {

        val dummyAPI = RetrofitCustom.recuperarDumyAPI()
        val usuarioRepository = UsuarioRepositoryImpl( dummyAPI )
        val usuarioUseCase = UsuarioUseCase( usuarioRepository )
        usuarioViewModel = ViewModelProvider(
            this, UsuarioViewModelFactory( usuarioUseCase )
        )[UsuarioViewModel::class.java]

    }
}