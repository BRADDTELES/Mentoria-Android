package com.danilloteles.aulapokemonapiturma04

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonDTO
import com.danilloteles.aulapokemonapiturma04.databinding.ActivityMainBinding
import com.danilloteles.aulapokemonapiturma04.presentation.adapter.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
        inicializarComponentesInterface()

    }

    private fun inicializarComponentesInterface() {

        with( binding ){
            pokemonAdapter = PokemonAdapter()
            rvPokemons.adapter = pokemonAdapter
            rvPokemons.layoutManager = GridLayoutManager(
                applicationContext, 3
            )
            pokemonAdapter.adicionarLista(
                listOf(
                    PokemonDTO("Bulbasauro", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                    PokemonDTO("Pikachu", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                    PokemonDTO("Charmander", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                    PokemonDTO("Onix", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                    PokemonDTO("Bulbasauro", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                    PokemonDTO("Bulbasauro", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                    PokemonDTO("Bulbasauro", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"),
                )
            )
        }


    }
}