package com.danilloteles.testeempregopetz.data.model

data class Carta(
    val artist: String,
    val attack: Int,
    val cardId: String,
    val cardSet: String,
    val cost: Int,
    val dbfId: Int,
    val health: Int,
    val img: String,
    val locale: String,
    val mechanics: List<Mechanic>,
    val name: String,
    val playerClass: String,
    val race: String,
    val rarity: String,
    val text: String,
    val type: String
)