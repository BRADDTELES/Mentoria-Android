package com.danilloteles.aulapokemonapiturma04.data.remote.api.repository

import com.danilloteles.aulapokemonapiturma04.data.remote.api.PokemonAPI
import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonDTO
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    val pokemonAPI: PokemonAPI
) : IPokemonRepository {
    override suspend fun recuperarPokemons(): List<PokemonDTO> {
        try {
            val resultado = pokemonAPI.recuperarPokemons()
            if (resultado.isSuccessful && resultado.body() != null) {
                val listaPokemons = resultado.body()?.results
                if (listaPokemons != null) return listaPokemons
            }
        }catch ( erro: Exception ){
            erro.printStackTrace()
        }
        return emptyList()
    }
}