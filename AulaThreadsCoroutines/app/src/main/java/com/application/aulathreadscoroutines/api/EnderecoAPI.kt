package com.application.aulathreadscoroutines.api

import com.application.aulathreadscoroutines.model.Endereco
import retrofit2.Response
import retrofit2.http.GET

interface EnderecoAPI {


    // BASE URL:  https://viacep.com.br/ + ws/01001000/json/
    //GET, POST, PUT, PATH e DELETE
    @GET("ws/74375150/json/")
    suspend fun recuperarEndereco() : Response<Endereco>


}