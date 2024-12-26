package com.danilloteles.testeempregopetz.data.api

import retrofit2.http.GET

interface HearthstoneService {

    @GET("cards")
    suspend fun recuperarCartoes()
}