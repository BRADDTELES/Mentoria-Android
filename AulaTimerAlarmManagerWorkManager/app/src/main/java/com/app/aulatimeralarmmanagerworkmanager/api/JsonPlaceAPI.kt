package com.app.aulatimeralarmmanagerworkmanager.api

import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceAPI {

    @GET("posts")
    suspend fun recuperarPostagens() : Response<List<Postagem>>
}