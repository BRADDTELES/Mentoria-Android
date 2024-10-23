package com.application.aulathreadscoroutines.model

import com.google.gson.annotations.SerializedName

data class Postagem(
    @SerializedName("body")
    val description: String,
    val id: Int,
    val title: String,
    val userId: Int
)