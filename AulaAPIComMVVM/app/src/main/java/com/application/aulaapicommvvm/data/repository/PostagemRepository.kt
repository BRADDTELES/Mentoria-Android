package com.application.aulaapicommvvm.data.repository

import androidx.lifecycle.MutableLiveData
import com.application.aulaapicommvvm.data.api.JsonPlaceAPI
import com.application.aulaapicommvvm.data.model.PostagemResposta
import com.application.aulaapicommvvm.data.model.toPostagem
import com.application.aulaapicommvvm.domain.model.Postagem

class PostagemRepository(
    private val jsonPlaceAPI: JsonPlaceAPI
) : IPostagemRepository {

    //override val listaPostagensRepository = MutableLiveData<List<PostagemResposta>>()

    override suspend fun recuperarPostagens() : List<Postagem> {

        try {
            val retorno = jsonPlaceAPI.recuperarPostagens()
            if ( retorno.isSuccessful && retorno.body() != null){
                val listaPostagensResposta = retorno.body()
                val listaPostagens = listaPostagensResposta?.map { postagemResposta ->
                    postagemResposta.toPostagem()
                }
                if ( listaPostagens != null ){
                    return listaPostagens
                }
            }
        }catch (erroRecuperarPostagem: Exception){
            erroRecuperarPostagem.printStackTrace()
        }
        return emptyList()

        /*try {
            val retorno = jsonPlaceAPI.recuperarPostagens()
            if ( retorno.isSuccessful && retorno.body() != null){
                //listaPostagensRepository.postValue( retorno.body() )
            }
        }catch (erroRecuperarPostagem: Exception){
            erroRecuperarPostagem.printStackTrace()
        }*/

    }

}