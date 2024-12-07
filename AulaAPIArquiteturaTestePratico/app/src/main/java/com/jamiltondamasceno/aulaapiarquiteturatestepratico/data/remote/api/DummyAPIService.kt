package com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.api

import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.dto.ResultadoDummyAPI
import retrofit2.Response
import retrofit2.http.GET

interface DummyAPIService {

    @GET("users")//Facade -> Fachada
    suspend fun recupearListaUsuarios() : Response<ResultadoDummyAPI>

}