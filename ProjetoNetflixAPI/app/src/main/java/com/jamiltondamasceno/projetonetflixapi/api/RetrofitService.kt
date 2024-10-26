package com.jamiltondamasceno.projetonetflixapi.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_URL_IMAGEM = "https://image.tmdb.org/t/p/"
    const val API_KEY = "1c696cada346f99c183ec5afb21bfe81"
    const val TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYzY5NmNhZGEzNDZmOTljMTgzZWM1YWZiMjFiZmU4MSIsIm5iZiI6MTcyOTk1MjE1Ny43Njk1ODksInN1YiI6IjY3MTk1Mjc2ZmVmZDFlMDUxMGZmZDEzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Hnpu_6a2_RtlzYqWGC_1XlDQcTWouZF0MiW6eq5mZ9E"

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

    // Configurar API ViaCep usando método
    fun recuperarViaCep() : ViaCepAPI {

        return Retrofit.Builder()
            .baseUrl( "https://viacep.com.br/ws/" ) // URL base da API ViaCep
            .addConverterFactory( GsonConverterFactory.create() ) // Converter JSON para objeto Kotlin
            //.addConverterFactory( SimpleXmlConverterFactory.create() ) // Converter XML para objeto Kotlin
            .client( okhttpClient )
            .build()
            .create( ViaCepAPI::class.java )
    }

    /*// Configurar API ViaCep usando propriedade atributo
    val viaCepAPI = Retrofit.Builder()
        .baseUrl( BASE_URL )
        .addConverterFactory( GsonConverterFactory.create() )
        .client( okhttpClient )
        .build()
        .create( ViaCepAPI::class.java )*/
}