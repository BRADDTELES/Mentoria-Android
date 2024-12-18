package com.application.aulatestes.data.repository

class ViagemRepositoryImpl : InterfaceViagemRepository {
    override suspend fun listarLocais(): List<Pair<String, String>> {
        // recupera dados da API
        return listOf(
            Pair("-22.9027800", "-43.2075000"),
            Pair("48.8534100", "2.3488000")
        )
    }

    override suspend fun calcularPrecoViagem(distancia: Double, precoKM: Double): Double {
        // recupera dados da API
        return 50.0
    }
}