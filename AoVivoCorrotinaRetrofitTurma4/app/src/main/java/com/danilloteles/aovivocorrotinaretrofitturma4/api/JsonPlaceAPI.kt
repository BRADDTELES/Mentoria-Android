package com.danilloteles.aovivocorrotinaretrofitturma4.api

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface JsonPlaceAPI {//Facade -> Fachada

    //nubank.com : saldo, extrato, transfêNCIA, pix, renavam, recarga
    //https://jsonplaceholder.typicode.com/ + posts
    @GET("posts")
    suspend fun recuperarPostagens() : Response<List<Postagem>>

    /*@GET("users")
    suspend fun recuperarUsuários() : Response<String>*/

}