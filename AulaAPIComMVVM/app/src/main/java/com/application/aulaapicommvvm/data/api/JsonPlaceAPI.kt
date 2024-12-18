package com.application.aulaapicommvvm.data.api

import com.application.aulaapicommvvm.data.model.PostagemResposta
import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceAPI {
    @GET("posts")
    suspend fun recuperarPostagens() : Response<List<PostagemResposta>>
}