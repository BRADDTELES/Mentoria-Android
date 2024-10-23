package com.application.aulathreadscoroutines.api

import com.application.aulathreadscoroutines.model.Postagem
import retrofit2.Response
import retrofit2.http.GET

interface PostagemAPI {

    // https://jsonplaceholder.typicode.com/posts
    @GET("posts")
    suspend fun recuperarPostagens() : Response< List<Postagem> >
}