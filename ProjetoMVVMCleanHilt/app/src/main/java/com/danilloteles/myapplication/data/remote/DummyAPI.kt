package com.danilloteles.myapplication.data.remote

import com.danilloteles.myapplication.data.dto.ResultadoAPIDTO
import retrofit2.Response
import retrofit2.http.GET

interface DummyAPI {

    //https://dummyjson.com/users
    // getUsers()
    @GET("users")
    suspend fun recuperarUsuarios() : Response< ResultadoAPIDTO >
}