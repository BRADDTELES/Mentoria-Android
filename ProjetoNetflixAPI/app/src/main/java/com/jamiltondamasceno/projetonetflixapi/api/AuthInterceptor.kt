package com.jamiltondamasceno.projetonetflixapi.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //Chain: corrente, cadeia de interceptadores

        // 1) Acessar a requisição
        val construtorRequisicao = chain.request().newBuilder()

        // 2) Alterar URL ou Rota da requisição
        // https://api.themoviedb.org/3/ + movie/latest + api_key
        /*val novaUrl = chain.request().url().newBuilder().addQueryParameter(
            "api_key",
            RetrofitService.API_KEY
        )*/
        /*val urlAtual = chain.request().url()
        val novaUrl = urlAtual.newBuilder()
        novaUrl.addQueryParameter(
            "api_key",
            RetrofitService.API_KEY
        )

        // 3) Configurar nova URL na requisição
        construtorRequisicao.url( novaUrl.build() )*/

        //Utilizando Bearer Token
        val requisicao = construtorRequisicao.addHeader(
            "Authorization", "Bearer ${RetrofitService.TOKEN}" // no value dentro da string precisa colocar Barear e o Token
        ).build()

        //return chain.proceed( construtorRequisicao.build() )//Response
        return chain.proceed( requisicao )//Response
    }
}