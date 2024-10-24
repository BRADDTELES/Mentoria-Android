package com.application.aulathreadscoroutines.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitHelper {
    companion object {
        val apiViaCEP = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/")
            .addConverterFactory( GsonConverterFactory.create() )//json ou XML
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory( GsonConverterFactory.create() )//json ou XML
            .build()

        val filmeAPI = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory( GsonConverterFactory.create() )//json ou XML
            .build()
            .create( FilmeAPI::class.java )
    }
}