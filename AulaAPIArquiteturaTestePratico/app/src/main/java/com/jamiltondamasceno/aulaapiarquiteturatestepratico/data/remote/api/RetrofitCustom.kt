package com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCustom {

    fun recuperarRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")//
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
    }

    fun recuperarDumyAPI() : DummyAPIService {
        return recuperarRetrofit().create( DummyAPIService::class.java )
    }

}