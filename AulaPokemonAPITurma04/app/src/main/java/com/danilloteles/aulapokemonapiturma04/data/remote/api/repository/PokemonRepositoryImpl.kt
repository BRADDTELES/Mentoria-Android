package com.danilloteles.aulapokemonapiturma04.data.remote.api.repository

import android.util.Log
import com.danilloteles.aulapokemonapiturma04.data.remote.api.PokemonAPI
import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonDTO
import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonDetalheDTO
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    val pokemonAPI: PokemonAPI
) : IPokemonRepository {
    override suspend fun recuperarPokemons(): List<PokemonDTO> {
        try {
            val resultado = pokemonAPI.recuperarPokemons()
            if (resultado.isSuccessful && resultado.body() != null) {

                //https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg

                val listaPokemons = resultado.body()?.results?.map { pokemonDTO ->
                    pokemonDTO.url //https://pokeapi.co/api/v2/pokemon/1/
                    val idPokemon = if (pokemonDTO.url.endsWith("/")) {
                        pokemonDTO.url.dropLast(1).takeLastWhile { it.isDigit() }
                    }else{
                        pokemonDTO.url.takeLastWhile { it.isDigit() }
                    }
                    //val id = pokemonDTO.url.split("/").last { it.isNotEmpty() }.toInt()
                    val urlImagem = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/$idPokemon.png"
                    val nomePokemon = pokemonDTO.name.replaceFirstChar { caracter ->
                        caracter.titlecase()
                    }

                    PokemonDTO( nomePokemon , urlImagem)
                }

                if (listaPokemons != null) return listaPokemons
            }
        }catch ( erro: Exception ){
            erro.printStackTrace()
        }
        return emptyList()
    }

    override suspend fun recuperarPokemonDetalhe(nomePokemon: String ): PokemonDetalheDTO? {
        try {
            val resultado = pokemonAPI.recuperarPokemonDetalhe( nomePokemon.lowercase() )
            if ( resultado.isSuccessful ) {

                val pokemonDetalhe = resultado.body()
                if (pokemonDetalhe != null) return pokemonDetalhe

            }else{
                Log.i("info_pokemon","erro sucesso: ${resultado.errorBody()}")
            }
        }catch ( erro: Exception ){
            erro.printStackTrace()
            Log.i("info_pokemon","erro: ${erro.message}")
        }
        return null
    }
}