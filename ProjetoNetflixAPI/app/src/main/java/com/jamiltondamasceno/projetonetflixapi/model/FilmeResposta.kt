package com.jamiltondamasceno.projetonetflixapi.model

import com.google.gson.annotations.SerializedName

data class FilmeResposta(
    val page: Int,
    @SerializedName("results") // Anotação para mapear o campo "results" da resposta para a propriedade "filmes"
    val filmes: List<Filme>,
    val total_pages: Int,
    val total_results: Int
)