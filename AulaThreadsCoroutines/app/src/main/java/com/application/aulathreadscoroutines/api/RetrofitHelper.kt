package com.application.aulathreadscoroutines.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitHelper {
    companion object {

        const val APY_KEY = "1c696cada346f99c183ec5afb21bfe81"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/"
        const val TAMANHO_W1280 = "w1280"

        val apiViaCEP = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/")
            .addConverterFactory( GsonConverterFactory.create() )//json ou XML
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory( GsonConverterFactory.create() )//json ou XML
            .build()

        val filmeAPI = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory( GsonConverterFactory.create() )//json ou XML
            .build()
            .create( FilmeAPI::class.java )
    }
}