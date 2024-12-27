package com.danilloteles.testeempregopetz.data.repository

import android.util.Log
import com.danilloteles.testeempregopetz.data.api.HearthstoneService
import com.danilloteles.testeempregopetz.data.model.Carta
import javax.inject.Inject

class CartaRepositoryImpl @Inject constructor(
    private val hearthstoneService: HearthstoneService
) : CartaRepository {
    override suspend fun recuperarCartas(): List<Carta> {

        val resposta = hearthstoneService.recuperarCartoes("Basic")
        if ( resposta.isSuccessful  ) {

            val listaCartas = resposta.body()
            if (listaCartas != null) {
                return listaCartas
            }

        }else{
            Log.i("mensagem_api_cartas", resposta.message())
        }

        return emptyList()

    }
}