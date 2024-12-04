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
      // Dado
      val mensagem = "Mensagem de Alerta"
      val mensagemEsperada = true

      // Quando
      operacoesBasicas.exibirAlerta(mensagem)

      // Então
      assertThat(mensagemEsperada).isTrue()
   }

   @Test
   fun recuperarDadosAPI() = runTest() {
      // Dado
      val lista = listOf(
         "jamilton", "ana", "maria", "pedro"
      )
      // Quando
      val nomesRecebidos = operacoesBasicas.recuperarDadosAPI(lista)

      // Então
      assertThat(
         nomesRecebidos
      ).isNotEmpty()
   }

   @After
   fun tearDown() {
   }
}