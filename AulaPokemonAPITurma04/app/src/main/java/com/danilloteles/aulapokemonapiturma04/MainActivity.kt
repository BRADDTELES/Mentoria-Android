package com.danilloteles.aulapokemonapiturma04

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonDTO
import com.danilloteles.aulapokemonapiturma04.databinding.ActivityMainBinding
import com.danilloteles.aulapokemonapiturma04.presentation.adapter.PokemonAdapter
import com.danilloteles.aulapokemonapiturma04.presentation.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var pokemonAdapter: PokemonAdapter
    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        pokemonViewModel.recuperarPokemons()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
        inicializarComponentesInterface()
        inicializarObservaveis()

    }

    private fun inicializarObservaveis() {

        pokemonViewModel.listaPokemons.observe(this){ lista ->
            pokemonAdapter.adicionarLista( lista )
        }

    }

    private fun inicializarComponentesInterface() {

        with( binding ){
            pokemonAdapter = PokemonAdapter{ pokemonDTO ->
                val intent = Intent(applicationContext, PokemonDetalheActivity::class.java)
                intent.putExtra("pokemon", pokemonDTO)
                startActivity( intent )
            }
            rvPokemons.adapter = pokemonAdapter
            rvPokemons.layoutManager = GridLayoutManager(
                applicationContext, 3
            )

        }
    }
}