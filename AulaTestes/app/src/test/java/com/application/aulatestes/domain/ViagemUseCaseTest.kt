package com.application.aulatestes.domain

import com.application.aulatestes.data.repository.FakeViagemRepository
import com.application.aulatestes.data.repository.InterfaceViagemRepository
import com.application.aulatestes.data.repository.ViagemRepositoryImpl
import com.application.aulatestes.regras.RegraCustomizada
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith( MockitoJUnitRunner::class )
class ViagemUseCaseTest {

   private lateinit var viagemUseCase: ViagemUseCase
   //private lateinit var interfaceViagemRepository: InterfaceViagemRepository
   //private lateinit var fakeViagemRepository: FakeViagemRepository

   @Mock
   private lateinit var mockViagemRepositoryImpl: ViagemRepositoryImpl

   @get:Rule
   val regra = RegraCustomizada("100")

   @Before
   fun setUp() {
      //interfaceViagemRepository = FakeViagemRepository()

      //fakeViagemRepository = FakeViagemRepository()
      //viagemUseCase = ViagemUseCase(fakeViagemRepository)

      println("setUp")
      MockitoAnnotations.openMocks(this)
      viagemUseCase = ViagemUseCase(mockViagemRepositoryImpl)

   }

   @Test
   fun listarLocais() = runTest {

      /* Minha Resposta
      val listaDeLocais = interfaceViagemRepository.listarLocais()

      assertThat(
         listaDeLocais
      ).isNotEmpty()*/

      /* Resposta do Professor */
      println("+ listarLocais")
      regra.log = "100"
      Mockito.`when`( mockViagemRepositoryImpl.listarLocais()).thenReturn(
         listOf(
            Pair("-22.9027800", "-43.2075000"),
            Pair("48.8534100", "2.3488000")
         )
      )


      val lista = viagemUseCase.listarLocais()

      assertThat(lista).isNotEmpty()

   }

   @Test
   fun calcularPrecoViagem() = runTest {

      /* Minha Resposta
      // Dado
      val distancia = 10.0
      val precoKM = 5.0
      val retornoEsperado = 50.0

      // Quando
      val precoCalculado = interfaceViagemRepository.calcularPrecoViagem(distancia, precoKM)

      // Então
      assertThat(
         precoCalculado
      ).isEqualTo(retornoEsperado)*/

      /* Resposta do Professor */
      println("+ calcularPrecoViagem")
      regra.log = "200"
      val distancia = 10.0
      val precoKM = 5.0
      val retornoEsperado = 50.0

      //anyString, anytInt, anyBoolean...
      Mockito.`when`(
         mockViagemRepositoryImpl.calcularPrecoViagem(
            anyDouble(), anyDouble()
         )
      ).thenReturn(
         50.0
      )

      val retorno = viagemUseCase.calcularPrecoViagem(distancia, precoKM)
      assertThat(retorno).isEqualTo(retornoEsperado)
   }

   @After
   fun tearDown() {
      println("tearDown")
   }
}