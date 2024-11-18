package com.application.aulaapicommvvm.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    fun recuperarJsonPlace() : JsonPlaceAPI {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create( JsonPlaceAPI::class.java )
    }

}