package com.danilloteles.aulapokemonapiturma04.data.remote.api.repository

import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonDTO

//recuperarPokemons, SalvarPokemon, AtualizarPokemon
interface IPokemonRepository {
    suspend fun recuperarPokemons() : List<PokemonDTO>
}