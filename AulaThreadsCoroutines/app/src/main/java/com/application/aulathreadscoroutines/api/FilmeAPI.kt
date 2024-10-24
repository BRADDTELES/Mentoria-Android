package com.application.aulathreadscoroutines.api

import com.application.aulathreadscoroutines.model.FilmeResposta
import retrofit2.Response
import retrofit2.http.GET

interface FilmeAPI {

    @GET("movie/popular?api_key=${RetrofitHelper.APY_KEY}")
    suspend fun recuperarFilmesPopulares(): Response<FilmeResposta>

}