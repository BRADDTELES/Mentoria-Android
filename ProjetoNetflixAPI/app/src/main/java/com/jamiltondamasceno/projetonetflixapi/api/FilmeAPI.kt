package com.jamiltondamasceno.projetonetflixapi.api

import com.jamiltondamasceno.projetonetflixapi.model.FilmeRecente
import com.jamiltondamasceno.projetonetflixapi.model.FilmeResposta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmeAPI {

    // Recuperar o filme mais recente
    @GET("movie/latest?api_key=${RetrofitService.API_KEY}")
    suspend fun recuperarFilmeRecente(): Response<FilmeRecente>

    // Recuperar os filmes mais populares
    @GET("movie/popular?api_key=${RetrofitService.API_KEY}")
    suspend fun recuperarFilmesPopulares(
        @Query("page") pagina: Int
    ): Response<FilmeResposta>

}