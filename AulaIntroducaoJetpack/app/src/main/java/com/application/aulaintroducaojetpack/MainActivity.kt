package com.application.aulaintroducaojetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.application.aulaintroducaojetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        //mainViewModel = MainViewModel()

        mainViewModel = ViewModelProvider(this)[MainViewModel::class]
        /*val textoContador = mainViewModel.recuperarLiveData()
        binding.textResultado.text = "cliques: $textoContador"*/

        //Observador
        val liveData = mainViewModel.recuperarLiveData()
        liveData.observe(this){ contador ->
            binding.textResultado.text = "cliques: $contador"
        }

        binding.btnClique.setOnClickListener {
            mainViewModel.incrementar()
            //binding.textResultado.text = "cliques: ${mainViewModel.recuperarLiveData()}"
        }

    }
}