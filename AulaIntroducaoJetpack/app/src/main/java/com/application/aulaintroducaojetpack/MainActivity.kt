package com.application.aulaintroducaojetpack

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.application.aulaintroducaojetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var mainViewModel: MainViewModel
    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        //DataBinding
        dataBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        //Dado
        val usuario = Usuario("Jamilton Damasceno", "35")
        dataBinding.usuario = usuario
        dataBinding.clique = EventoClique(this)

        //mainViewModel = MainViewModel()

        mainViewModel = ViewModelProvider(this)[MainViewModel::class]
        /*val textoContador = mainViewModel.recuperarLiveData()
        binding.textResultado.text = "cliques: $textoContador"*/

        //Observador
        val liveData = mainViewModel.recuperarLiveData()
        liveData.observe(this){ contador ->
            binding.textResultado.text = "cliques: $contador"
        }

        /*binding.btnClique.setOnClickListener {
            mainViewModel.incrementar()
            //binding.textResultado.text = "cliques: ${mainViewModel.recuperarLiveData()}"
        }*/

    }

    inner class EventoClique(
        private val context: Context
    ){
        fun executarAcao(view: View){
            Toast.makeText(context, "Botão clicado", Toast.LENGTH_SHORT).show()

        }
    }

}