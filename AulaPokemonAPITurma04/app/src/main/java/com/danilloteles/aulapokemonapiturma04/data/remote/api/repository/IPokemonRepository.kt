package com.danilloteles.aulapokemonapiturma04.data.remote.api.repository

import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonDTO
import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonDetalheDTO

//recuperarPokemons, SalvarPokemon, AtualizarPokemon
interface IPokemonRepository {
    suspend fun recuperarPokemons() : List<PokemonDTO>
    suspend fun recuperarPokemonDetalhe(nomePokemon: String ) : PokemonDetalheDTO?
}