package com.application.aulatestes.data.repository

import com.application.aulatestes.data.model.Usuario
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.time.Duration.Companion.seconds

class UsuarioRepositoryImplTest {

   private lateinit var usuarioRepository: UsuarioRepository

   @Before
   fun setUp() {
      usuarioRepository = UsuarioRepositoryImpl()
   }

   @After
   fun tearDown() {
   }

   //fun funcaoEmLinha() = println()

   /*@Test
   fun salvar_dadosUsuarioAPI_retornaVerdadeiro() = runBlocking() {
      //dado
      val usuario = Usuario("jamilton", "j@gmaill.com")

      //quando
      val retorno = usuarioRepository.salvar( usuario )

      //então
      assertTrue(retorno)

   }*/

   /*@Test
   fun listar_temItensUsuariosAPI_retornaVerdadeiro() = runBlocking {

      //dado

      //quando
      val listaItens = usuarioRepository.listar()

      //então
      assertTrue( listaItens.isNotEmpty() )

   }*/

   //runTest(timeout = 30.seconds)
   @Test
   fun salvar_dadosUsuarioAPI_retornaVerdadeiro() = runTest {
      //dado
      val usuario = Usuario("jamilton", "j@gmaill.com")

      //quando
      val retorno = usuarioRepository.salvar( usuario )

      //então
      assertTrue(retorno)

   }

   @Test
   fun listar_temItensUsuariosAPI_retornaVerdadeiro() = runTest {

      //dado

      //quando
      val listaItens = usuarioRepository.listar()

      //então
      assertTrue( listaItens.isNotEmpty() )

   }
}