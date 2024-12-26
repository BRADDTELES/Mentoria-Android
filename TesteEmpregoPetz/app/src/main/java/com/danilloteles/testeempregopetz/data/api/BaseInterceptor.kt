package com.danilloteles.testeempregopetz.data.api

import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requisicaoBuilder = chain.request().newBuilder()

        requisicaoBuilder.addHeader(
            "x-rapidapi-key", "e2c47a8818msh16a9e832ef8c959p1a59adjsn36f94055d710"
        )
        requisicaoBuilder.addHeader(
            "x-rapidapi-host", "omgvamp-hearthstone-v1.p.rapidapi.com"
        )

        return chain.proceed( requisicaoBuilder.build() )
    }
}