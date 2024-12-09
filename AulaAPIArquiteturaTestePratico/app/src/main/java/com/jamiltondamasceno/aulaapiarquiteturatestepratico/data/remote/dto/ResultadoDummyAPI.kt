package com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.dto

data class ResultadoDummyAPI(
    val limit: Int,
    val skip: Int,
    val total: Int,
    val users: List<Usuario>
)