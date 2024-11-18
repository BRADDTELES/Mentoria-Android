package com.application.aulaapicommvvm.api

import com.application.aulaapicommvvm.model.Postagem
import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceAPI {
    @GET("posts")
    suspend fun recuperarPostagens() : Response<List<Postagem>>
}