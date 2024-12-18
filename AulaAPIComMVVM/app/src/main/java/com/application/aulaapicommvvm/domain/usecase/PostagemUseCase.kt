package com.application.aulaapicommvvm.domain.usecase

import com.application.aulaapicommvvm.data.repository.IPostagemRepository
import com.application.aulaapicommvvm.domain.model.Postagem

//Autenticação ddde usuário
// + Validação de todos os campos (nome, e-mail, telefone)
// + Salvar o usuário
class PostagemUseCase(//GetPostagemUseCase ou SetPostagemUseCase
    private val repository: IPostagemRepository
) {

    //PostagemUI
    //suspend fun recuperarPostagens() : List<Postagem>{
    suspend operator fun invoke() : List<Postagem>{// Usar esse operator invoke somente quando tiver um Unico metodo

        try {
            //Tags da postagem
            //Fotos da postagem
            //Postagens semelhantes
            return repository.recuperarPostagens()
        }catch (erroRecuperarPostagens: Exception){
            erroRecuperarPostagens.printStackTrace()
        }
        return emptyList()
    }
}