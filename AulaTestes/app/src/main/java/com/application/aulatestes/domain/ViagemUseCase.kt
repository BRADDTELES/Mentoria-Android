package com.application.aulatestes.domain

import com.application.aulatestes.data.repository.InterfaceViagemRepository

class ViagemUseCase(
   private val interfaceViagemRepository: InterfaceViagemRepository
) {

   suspend fun listarLocais() : List<Pair<String, String>> {
      //Processamento de verificar itens nesta lista, formatações, remover itens da lista que não são válidos
      val lista = interfaceViagemRepository.listarLocais()
      //Processamento
      return lista
   }

   suspend fun calcularPrecoViagem(distancia: Double, precoKM: Double) : Double {
      //Recuperar dados da API: Distancia
      //Processamento
      return interfaceViagemRepository.calcularPrecoViagem(distancia, precoKM)
   }
}