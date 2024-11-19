package com.application.aulaapicommvvm.domain.usecase

import com.application.aulaapicommvvm.data.repository.IPostagemRepository

class PostagemUseCase(
    private val repository: IPostagemRepository
) {

    suspend fun recuperarPostagens(){
        val listaPostagens = repository.recuperarPostagens()
    }
}