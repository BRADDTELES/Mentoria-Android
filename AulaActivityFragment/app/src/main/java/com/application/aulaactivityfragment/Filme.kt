package com.application.aulaactivityfragment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Filme(
    val nome: String,
    val descricao: String,
    val avaliacao: Double,
    val diretor: String,
    val distribuidor: String
) : Parcelable


/*data class Filme(
    val nome: String,
    val descricao: String,
    val avaliacao: Double,
    val diretor: String,
    val distribuidor: String
) : Serializable*/
