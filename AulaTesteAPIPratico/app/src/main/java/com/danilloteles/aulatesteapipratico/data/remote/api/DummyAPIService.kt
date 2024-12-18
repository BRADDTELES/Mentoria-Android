package com.danilloteles.aulatesteapipratico.data.remote.api

import com.danilloteles.aulatesteapipratico.data.remote.dto.ResultadoDummyAPI
import retrofit2.Response
import retrofit2.http.GET

interface DummyAPIService {

    @GET("/users")
    suspend fun recuperarListaUsuarios() : Response<ResultadoDummyAPI>

}