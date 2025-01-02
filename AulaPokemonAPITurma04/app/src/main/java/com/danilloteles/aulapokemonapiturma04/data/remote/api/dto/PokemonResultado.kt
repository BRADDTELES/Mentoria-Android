package com.danilloteles.aulapokemonapiturma04.data.remote.api.dto

data class PokemonResultado(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonDTO>
)