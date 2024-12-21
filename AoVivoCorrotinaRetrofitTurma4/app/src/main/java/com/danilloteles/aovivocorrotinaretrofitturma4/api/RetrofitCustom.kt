package com.danilloteles.aovivocorrotinaretrofitturma4.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitCustom {

    /*val jsonPlace by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create( JsonPlaceAPI::class.java )
    }*/

    fun recuperarJsonPlaceAPI() : JsonPlaceAPI {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create( JsonPlaceAPI::class.java )
    }


}