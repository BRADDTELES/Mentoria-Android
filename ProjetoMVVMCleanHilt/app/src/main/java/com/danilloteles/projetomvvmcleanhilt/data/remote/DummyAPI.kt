package com.danilloteles.projetomvvmcleanhilt.data.remote

import com.danilloteles.projetomvvmcleanhilt.data.dto.ResultadoAPIDTO
import retrofit2.Response
import retrofit2.http.GET

interface DummyAPI {

    //https://dummyjson.com/users
    // getUsers()
    @GET("users")
    suspend fun recuperarUsuarios() : Response< ResultadoAPIDTO >
}