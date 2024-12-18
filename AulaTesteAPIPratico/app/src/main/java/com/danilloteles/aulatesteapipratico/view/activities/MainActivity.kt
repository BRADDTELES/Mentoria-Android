package com.danilloteles.aulatesteapipratico.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.danilloteles.aulatesteapipratico.R
import com.danilloteles.aulatesteapipratico.data.remote.api.RetrofitCustom
import com.danilloteles.aulatesteapipratico.data.remote.repository.UsuarioRepositoryImpl
import com.danilloteles.aulatesteapipratico.databinding.ActivityMainBinding
import com.danilloteles.aulatesteapipratico.domain.UsuarioUseCase
import com.danilloteles.aulatesteapipratico.view.viewmodel.UsuarioViewModel
import com.danilloteles.aulatesteapipratico.view.viewmodel.UsuarioViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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
            listaUsuarios.forEach{ usuario ->
                dadosUsuarios += "+ ${usuario.firstName} (${usuario.age}) \n"
                dadosUsuarios += "---------------- \n"
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