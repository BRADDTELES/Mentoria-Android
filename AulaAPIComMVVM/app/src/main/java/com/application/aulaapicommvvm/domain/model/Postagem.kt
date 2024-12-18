package com.application.aulaapicommvvm.domain.model

data class Postagem (
    val descricao: String,
    val codigo: Int,
    val titulo: String,
    val idUsuario: Int
){
    fun validarPostagem(){}
    fun trocarStatusPostagem(){}
    fun selecionarImagemPostagem(){}
}