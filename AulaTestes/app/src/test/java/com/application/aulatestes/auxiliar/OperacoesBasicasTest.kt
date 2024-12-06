package com.application.aulatestes.auxiliar

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test

class OperacoesBasicasTest {

   private lateinit var operacoesBasicas: OperacoesBasicas

   @Before
   fun setUp() {
      operacoesBasicas = OperacoesBasicas()
   }

   @Test
   fun formataData() {
      // Dado
      val dataSemFormatacao = "22102025"
      val dataEsperada = "22/10/2025"

      // Quando
      val dataFormatada = operacoesBasicas.formataData(dataSemFormatacao)

      // Então
      assertThat(dataFormatada).isEqualTo(dataEsperada)
   }

   @Test
   fun exibirAlerta() {
      /* Minha Resposta
      // Dado
      val mensagem = "Mensagem de Alerta"
      val mensagemEsperada = true

      // Quando
      operacoesBasicas.exibirAlerta(mensagem)

      // Então
      assertThat(mensagemEsperada).isTrue()*/

      /* Resposta do Professor */
      val retorno = operacoesBasicas.exibirAlerta()

      assertThat(retorno).isTrue()
   }

   @Test
   fun recuperarDadosAPI() = runTest() {
      /* Minha Resposta
      // Dado
      val lista = listOf(
         "jamilton", "ana", "maria", "pedro"
      )
      // Quando
      val nomesRecebidos = operacoesBasicas.recuperarDadosAPI(lista)

      // Então
      assertThat(
         nomesRecebidos
      ).isNotEmpty()*/

      /* Resposta do Professor */
      val lista = operacoesBasicas.recuperarDadosAPI()

      assertThat(lista).isNotEmpty()
   }

   @After
   fun tearDown() {
   }
}