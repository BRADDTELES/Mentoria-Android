package com.application.aulaapicommvvm.data.model

import com.application.aulaapicommvvm.domain.model.Postagem

data class PostagemResposta(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)

fun PostagemResposta.toPostagem() : Postagem{
     return Postagem(
        descricao = this.body,
        titulo = this.title,
        codigo = this.id,
        idUsuario = this.userId
    )
}