package com.jamiltondamasceno.projetonetflixapi.api

import com.jamiltondamasceno.projetonetflixapi.model.Endereco
import retrofit2.Response
import retrofit2.http.GET

interface ViaCepAPI {

    @GET("74915250/json")// retornando o endereço em formato JSON
    //@GET("01001000/xml")// retornando o endereço em formato XML
    suspend fun recuperarEndereco(): Response<Endereco>
}