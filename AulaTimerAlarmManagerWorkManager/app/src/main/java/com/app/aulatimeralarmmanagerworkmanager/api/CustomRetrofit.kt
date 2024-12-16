package com.app.aulatimeralarmmanagerworkmanager.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CustomRetrofit {

    val jsonPlaceApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")//posts
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create( JsonPlaceAPI::class.java )
    }

}