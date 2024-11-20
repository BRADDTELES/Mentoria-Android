package com.danilloteles.myapplication.data.dto

data class ResultadoAPIDTO(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val usuariosDTO: List<UsuarioDTO>
)