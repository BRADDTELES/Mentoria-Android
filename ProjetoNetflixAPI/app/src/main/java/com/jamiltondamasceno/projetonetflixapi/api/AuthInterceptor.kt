package com.jamiltondamasceno.projetonetflixapi.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //Chain: corrente, cadeia de interceptadores

        // 1) Acessar a requisição
        val construtorRequisicao = chain.request().newBuilder()

        // 2) Alterar URL ou Rota da requisição
        // https://api.themoviedb.org/3/
        val urlAtual = chain.request().url()
        val novaUrl = urlAtual.newBuilder()
        novaUrl.addQueryParameter(
            "api_key",
            RetrofitService.API_KEY
        )
        /*val novaUrl = chain.request().url().newBuilder().addQueryParameter(
            "api_key",
            RetrofitService.API_KEY
        )*/

        // 3) Configurar nova URL na requisição
        construtorRequisicao.url( novaUrl.build() )

        return chain.proceed( construtorRequisicao.build() )//Response
    }
}