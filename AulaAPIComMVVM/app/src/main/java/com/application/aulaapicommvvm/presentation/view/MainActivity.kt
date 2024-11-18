package com.application.aulaapicommvvm.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.application.aulaapicommvvm.data.api.RetrofitService
import com.application.aulaapicommvvm.data.repository.PostagemBancoDadosRepository
import com.application.aulaapicommvvm.data.repository.PostagemFirebaseRepository
import com.application.aulaapicommvvm.data.repository.PostagemRepository
import com.application.aulaapicommvvm.databinding.ActivityMainBinding
import com.application.aulaapicommvvm.presentation.viewmodel.MainViewModel
import com.application.aulaapicommvvm.presentation.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        val jsonPlaceAPI = RetrofitService.recuperarJsonPlace()
        //val postagemRepository = PostagemRepository( jsonPlaceAPI )
        //val postagemRepository = PostagemFirebaseRepository(  )
        val postagemRepository = PostagemBancoDadosRepository(  )

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory( postagemRepository )
        )[MainViewModel::class]
        //mainViewModel = ViewModelProvider(this)[MainViewModel::class]

        mainViewModel.listaPostagens.observe(this){ listaPostagens ->

            var listaResultado = ""
            listaPostagens.forEach { postagem ->
                listaResultado += "${postagem.id}) ${postagem.title} \n"
            }
            binding.textResultado.text = listaResultado

        }

    }

    override fun onStart() {
        super.onStart()
        mainViewModel.recuperarPostagens()
    }
}