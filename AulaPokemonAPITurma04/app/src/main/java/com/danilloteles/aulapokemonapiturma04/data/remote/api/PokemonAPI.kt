package com.danilloteles.aulapokemonapiturma04.data.remote.api

import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonDetalheDTO
import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonResultado
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {

    @GET("pokemon")
    suspend fun recuperarPokemons( ) : Response<PokemonResultado>

    @GET("pokemon/{pokemon}")
    suspend fun recuperarPokemonDetalhe(
        @Path("pokemon") pokemon: String
    ) : Response<PokemonDetalheDTO>

}