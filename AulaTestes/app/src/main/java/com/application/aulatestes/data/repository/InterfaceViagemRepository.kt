package com.application.aulatestes.data.repository

interface InterfaceViagemRepository {
   suspend fun listarLocais() : List<Pair<String, String>>
   suspend fun calcularPrecoViagem(distancia: Double, precoKM: Double) : Double
}