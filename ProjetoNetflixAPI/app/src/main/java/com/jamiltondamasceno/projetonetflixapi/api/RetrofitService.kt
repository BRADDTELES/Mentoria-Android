package com.jamiltondamasceno.projetonetflixapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object {

        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "1c696cada346f99c183ec5afb21bfe81"
        const val BASE_URL_IMAGEM = "https://image.tmdb.org/t/p/"


        val retrofit = Retrofit.Builder()
            .baseUrl( BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()

        val filmeAPI = retrofit.create( FilmeAPI::class.java )
    }
}