package com.jamiltondamasceno.projetonetflixapi.model

data class FilmeRecente(
    val adult: Boolean,
    val backdrop_path: Any,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: List<Genero>,
    val homepage: String,
    val id: Int,
    val imdb_id: Any,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Int,
    val poster_path: Any,
    val production_companies: List<Any>,
    val production_countries: List<Any>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<Any>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Int,
    val vote_count: Int
)