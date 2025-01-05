package com.danilloteles.aularxjava.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitCustom {

   val jsonPlace by lazy {
      Retrofit.Builder()
         .baseUrl("https://jsonplaceholder.typicode.com/")
         .addConverterFactory( GsonConverterFactory.create() )
         .addCallAdapterFactory( RxJava3CallAdapterFactory.create() )
         .build()
         .create( JsonPlaceApi::class.java )
   }
}