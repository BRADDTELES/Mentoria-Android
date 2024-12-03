package com.application.aulatestes.data.repository

import com.application.aulatestes.data.model.Usuario
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class UsuarioRepositoryImplTest {

   private lateinit var usuarioRepository: UsuarioRepository

   @Before
   fun setUp() {
      usuarioRepository = UsuarioRepositoryImpl()
   }

   @After
   fun tearDown() {
   }

   @Test
   fun salvar_dadosUsuarioAPI_retornaVerdadeiro() {
      //dado
      val usuario = Usuario("jamilton", "j@gmaill.com")

      //quando
      val retorno = usuarioRepository.salvar( usuario )

      //então
      assertTrue(retorno)

   }

   @Test
   fun listar_temItensUsuariosAPI_retornaVerdadeiro() {

      //dado

      //quando
      val listaItens = usuarioRepository.listar()

      //então
      assertTrue( listaItens.isNotEmpty() )

   }
}