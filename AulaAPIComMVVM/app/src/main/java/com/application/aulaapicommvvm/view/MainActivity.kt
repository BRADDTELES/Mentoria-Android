package com.application.aulaapicommvvm.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.application.aulaapicommvvm.databinding.ActivityMainBinding
import com.application.aulaapicommvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        mainViewModel = ViewModelProvider(this)[MainViewModel::class]

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