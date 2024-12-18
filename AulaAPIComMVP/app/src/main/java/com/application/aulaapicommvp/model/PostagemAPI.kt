package com.application.aulaapicommvp.model

import com.application.aulaapicommvp.api.RetrofitService

class PostagemAPI {

    suspend fun recuperarPostagens() : List<Postagem> {

        val jsonPlace = RetrofitService.recuperarJsonPlace()

        try {
            val retorno = jsonPlace.recuperarPostagens()
            if ( retorno.isSuccessful){
                retorno.body()?.let { postagens ->

                    return postagens
                }
            }
        }catch (erroRecuperarPostagem: Exception){
            erroRecuperarPostagem.printStackTrace()
        }

        return emptyList()
    }

}