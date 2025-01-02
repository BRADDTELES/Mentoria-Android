package com.danilloteles.aulapokemonapiturma04.data.remote.api

import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonResultado
import retrofit2.Response
import retrofit2.http.GET

interface PokemonAPI {

    @GET("pokemon")
    suspend fun recuperarPokemons( ) : Response<PokemonResultado>

}