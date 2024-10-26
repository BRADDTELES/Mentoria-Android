package com.jamiltondamasceno.projetonetflixapi.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_URL_IMAGEM = "https://image.tmdb.org/t/p/"
    const val API_KEY = "1c696cada346f99c183ec5afb21bfe81"

    private val okhttpClient: OkHttpClient = OkHttpClient.Builder()
        .writeTimeout(10, TimeUnit.SECONDS)// Escrita (salvando na API)
        .readTimeout(20, TimeUnit.SECONDS)// Leitura (recuperando dados da API)
        .connectTimeout(20, TimeUnit.SECONDS)// Tempo máximo de conexão
        .addInterceptor( AuthInterceptor() )
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl( BASE_URL )
        .addConverterFactory( GsonConverterFactory.create() )
        .client( okhttpClient )
        .build()

    val filmeAPI = retrofit.create( FilmeAPI::class.java )
}