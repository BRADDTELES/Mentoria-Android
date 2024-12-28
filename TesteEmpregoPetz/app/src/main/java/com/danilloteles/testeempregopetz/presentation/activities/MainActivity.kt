package com.danilloteles.testeempregopetz.presentation.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.danilloteles.testeempregopetz.R
import com.danilloteles.testeempregopetz.data.model.Carta
import com.danilloteles.testeempregopetz.databinding.ActivityMainBinding
import com.danilloteles.testeempregopetz.presentation.CartaoAdapter
import com.danilloteles.testeempregopetz.presentation.viewmodels.CartoesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val cartoesViewModel: CartoesViewModel by viewModels()
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var cartaoAdapter: CartaoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
        incializarRecyclerView()
        inicializarObservables()
    }

    private fun incializarRecyclerView() {

        cartaoAdapter = CartaoAdapter()
        binding.rvCartas.adapter = cartaoAdapter
        binding.rvCartas.layoutManager = GridLayoutManager(this, 3)

    }

    private fun inicializarObservables() {

        cartoesViewModel.listaCartas.observe(this){ listaCartas ->
            /*var lista = ""
            listaCartas.forEach { carta ->
                lista += " ${carta.cardId} \n"
            }
            Log.i("mensagem_api_cartas", lista )*/

            val listaCartasNova = mutableListOf<Carta>()
            listaCartas.forEach { carta ->
                if (carta.img != null) {
                    listaCartasNova.add(carta)
                }

            }

            if ( listaCartasNova.isNotEmpty() ) {
                cartaoAdapter.atualizarLista( listaCartasNova )
            }
        }

    }

    override fun onStart() {
        super.onStart()
        cartoesViewModel.recuperarCartoes()
    }
}