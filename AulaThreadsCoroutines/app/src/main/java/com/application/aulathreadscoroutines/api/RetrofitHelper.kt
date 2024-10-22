package com.application.aulathreadscoroutines.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory( GsonConverterFactory.create() )//json ou XML
            .build()
    }

}