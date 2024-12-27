package com.danilloteles.testeempregopetz.data.repository

import com.danilloteles.testeempregopetz.data.model.Carta

interface CartaRepository {
    suspend fun recuperarCartas() : List<Carta>
}