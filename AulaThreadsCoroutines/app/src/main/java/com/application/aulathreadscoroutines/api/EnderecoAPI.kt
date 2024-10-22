package com.application.aulathreadscoroutines.api

import com.application.aulathreadscoroutines.model.Endereco
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoAPI {


    // BASE URL:  https://viacep.com.br/ + ws/01001000/json/
    //GET, POST, PUT, PATH e DELETE
    @GET("ws/{cep}/json/")
    suspend fun recuperarEndereco(
        @Path("cep") cep: String
    ) : Response<Endereco>


}