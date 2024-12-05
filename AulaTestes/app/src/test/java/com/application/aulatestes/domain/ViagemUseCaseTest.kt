package com.application.aulatestes.domain

import com.application.aulatestes.data.repository.FakeViagemRepository
import com.application.aulatestes.exercicio.InterfaceViagemRepository
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat

import org.junit.After
import org.junit.Before
import org.junit.Test

class ViagemUseCaseTest {

   private lateinit var viagemUseCase: ViagemUseCase
   private lateinit var interfaceViagemRepository: InterfaceViagemRepository

   @Before
   fun setUp() {
      interfaceViagemRepository = FakeViagemRepository()
   }

   @Test
   fun listarLocais() = runBlocking {

      val listaDeLocais = interfaceViagemRepository.listarLocais()

      assertThat(
         listaDeLocais
      ).isNotEmpty()

   }

   @Test
   fun calcularPrecoViagem() = runBlocking {

      // Dado
      val distancia = 10.0
      val precoKM = 5.0
      val retornoEsperado = 50.0

      // Quando
      val precoCalculado = interfaceViagemRepository.calcularPrecoViagem(distancia, precoKM)

      // Então
      assertThat(
         precoCalculado
      ).isEqualTo(retornoEsperado)

   }

   @After
   fun tearDown() {
   }
}