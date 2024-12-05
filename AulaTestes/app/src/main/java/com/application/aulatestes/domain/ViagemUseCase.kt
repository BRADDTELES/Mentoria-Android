package com.application.aulatestes.domain

import com.application.aulatestes.exercicio.InterfaceViagemRepository

class ViagemUseCase(
   private val interfaceViagemRepository: InterfaceViagemRepository
) {

   suspend fun listarLocais() : List<Pair<String, String>> {
      return interfaceViagemRepository.listarLocais()
   }

   suspend fun calcularPrecoViagem(distancia: Double, precoKM: Double) : Double {
      return interfaceViagemRepository.calcularPrecoViagem(distancia, precoKM)
   }
}