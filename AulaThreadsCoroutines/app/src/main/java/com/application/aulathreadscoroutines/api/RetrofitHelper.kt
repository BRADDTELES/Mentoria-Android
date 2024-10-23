package com.application.aulathreadscoroutines.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    }
}