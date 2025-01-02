package com.danilloteles.aulapokemonapiturma04

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonDTO
import com.danilloteles.aulapokemonapiturma04.databinding.ActivityPokemonDetalheBinding
import com.danilloteles.aulapokemonapiturma04.presentation.viewmodel.PokemonViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetalheActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPokemonDetalheBinding.inflate( layoutInflater )
    }
    private val pokemonViewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        inicializarObservaveis()
        recuperarDadosPokemon()


    }

    private fun recuperarDadosPokemon() {
        val bundle = intent.extras
        if (bundle != null) {
            val pokemonDto = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("pokemon", PokemonDTO::class.java)
            } else {
                bundle.getParcelable("pokemon")
            }

            if (pokemonDto != null) {
                pokemonViewModel.recuperarPokemonDetalhe( pokemonDto.name)
            }

        }
    }

    private fun inicializarObservaveis() {
        pokemonViewModel.pokemonDetalhe.observe(this){ pokemonDetalhe ->
            if ( pokemonDetalhe != null ) {
                binding.textNomePokemonDetalhe.text = pokemonDetalhe.name.replaceFirstChar { caracter ->
                    caracter.titlecase()
                }

                val imagem = pokemonDetalhe.sprites.other.home.front_default
                Picasso.get()
                    .load( imagem )
                    .into( binding.imagePokemonDetalhe )
                /*val imagem = pokemonDetalhe.sprites.other.showdown.front_default
                if (imagem != null) {
                    Glide
                        .with(this)
                        .asGif()
                        .load(imagem)
                        .into(binding.imagePokemonDetalhe);
                }*/

            }else{
                Log.i("info_pokemon","else observavel")
            }
        }
    }
}