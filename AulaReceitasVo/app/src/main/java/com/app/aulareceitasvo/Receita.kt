package com.app.aulareceitasvo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Receita(
    val titulo: String,
    val tempo: String,
    val resIdImagem: Int,
    val ingreditentes: List<String>
) : Parcelable

/*data class Receita(
    val titulo: String,
    val tempo: String,
    val resIdImagem: Int
) : Serializable*/
