package com.danilloteles.projetomvvmcleanhilt.data.dto

data class ResultadoAPIDTO(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val usuarioDTOS: List<UsuarioDTO>
)