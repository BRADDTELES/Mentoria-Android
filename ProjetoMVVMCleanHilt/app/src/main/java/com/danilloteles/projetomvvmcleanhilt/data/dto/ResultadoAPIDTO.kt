package com.danilloteles.projetomvvmcleanhilt.data.dto

import com.google.gson.annotations.SerializedName

data class ResultadoAPIDTO(
    val limit: Int,
    val skip: Int,
    val total: Int,
    @SerializedName("users")
    val usuariosDTO: List<UsuarioDTO>
)