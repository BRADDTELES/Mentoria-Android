package com.danilloteles.testeempregopetz.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Carta(
    val attack: Int = 0,
    val cardId: String = "",
    val cardSet: String = "",
    val cost: Int = 0,
    val dbfId: Int = 0,
    val health: Int = 0,
    val img: String = "",
    val locale: String = "",
    val name: String = "",
    val playerClass: String = "",
    val race: String = "",
    val text: String = "",
    val type: String = ""
) : Parcelable