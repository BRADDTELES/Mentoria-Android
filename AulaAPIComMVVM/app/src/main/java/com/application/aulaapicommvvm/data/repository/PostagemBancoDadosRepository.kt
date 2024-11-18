package com.application.aulaapicommvvm.data.repository

import androidx.lifecycle.MutableLiveData
import com.application.aulaapicommvvm.data.model.Postagem

class PostagemBancoDadosRepository() : IPostagemRepository {

    override val listaPostagensRepository = MutableLiveData<List<Postagem>>()
    override suspend fun recuperarPostagens() {
        val lista = listOf(
            Postagem(
                "Corpo", 10, "Titulo 1", 120
            ),
            Postagem(
                "Corpo", 11, "Titulo 2", 120
            ),
            Postagem(
                "Corpo", 12, "Titulo 3", 120
            ),
        )
        listaPostagensRepository.value = lista
    }
}