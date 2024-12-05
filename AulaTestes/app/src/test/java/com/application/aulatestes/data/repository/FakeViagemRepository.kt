package com.application.aulatestes.data.repository

import com.application.aulatestes.exercicio.InterfaceViagemRepository

class FakeViagemRepository : InterfaceViagemRepository {
   override suspend fun listarLocais(): List<Pair<String, String>> {
      return listOf(
         Pair("-22.9027800", "-43.2075000"),
         Pair("48.8534100", "2.3488000")
      )
   }

   override suspend fun calcularPrecoViagem(distancia: Double, precoKM: Double): Double {
      return distancia * precoKM
   }
}