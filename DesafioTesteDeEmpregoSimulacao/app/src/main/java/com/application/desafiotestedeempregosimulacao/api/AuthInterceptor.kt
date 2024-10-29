package com.application.desafiotestedeempregosimulacao.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain.request().newBuilder()

        val request = requestBuilder.addHeader(
            "Authorization",
            "Client-ID 6ce47f283a5067c"
        ).build()

        return chain.proceed( request )
    }
}