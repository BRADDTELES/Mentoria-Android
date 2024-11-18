package com.application.aulaapicommvvm.data.repository

import androidx.lifecycle.MutableLiveData
import com.application.aulaapicommvvm.data.model.Postagem

class PostagemFirebaseRepository : IPostagemRepository {

    override val listaPostagensRepository = MutableLiveData<List<Postagem>>()
    override suspend fun recuperarPostagens() {
        val lista = listOf(
            Postagem(
                "Corpo", 10, "Titulo", 120
            )
        )
        listaPostagensRepository.value = lista
    }



}