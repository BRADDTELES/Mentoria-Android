package com.application.aulathreadscoroutines.api

import com.application.aulathreadscoroutines.model.FilmeDetalhes
import com.application.aulathreadscoroutines.model.FilmeResposta
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmeAPI {

    @GET("movie/popular?api_key=${RetrofitHelper.APY_KEY}")
    suspend fun recuperarFilmesPopulares(): Response<FilmeResposta>

    @GET("movie/{idFilme}?api_key=${RetrofitHelper.APY_KEY}")
    suspend fun recuperarDetalhesFilme(
        @Path("idFilme") idFilme: Int
    ): Response<FilmeDetalhes>

    @GET("search/movie?api_key=${RetrofitHelper.APY_KEY}")
    suspend fun recuperarFilmePesquisa(
        @Query("query") pesquisa: String
    ): Response<FilmeResposta>
}