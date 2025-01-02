package com.danilloteles.aulapokemonapiturma04.data.remote.api.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonDTO(
    val name: String,
    val url: String
) : Parcelable