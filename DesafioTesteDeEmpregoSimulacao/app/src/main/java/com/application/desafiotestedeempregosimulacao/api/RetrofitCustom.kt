package com.application.desafiotestedeempregosimulacao.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCustom {
    //https://api.imgur.com/3/gallery/search/?q=cats

    val imgurAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.imgur.com/3/")
            .addConverterFactory( GsonConverterFactory.create() )
            .client(
                OkHttpClient.Builder()
                    .addInterceptor( AuthInterceptor() )
                    .build()
            )
            .build()
            .create( ImgurAPI::class.java )
    }

}