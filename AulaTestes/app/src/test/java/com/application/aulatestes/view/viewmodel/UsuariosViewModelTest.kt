package com.application.aulatestes.view.viewmodel

import com.google.common.truth.Truth.assertThat
import com.application.aulatestes.data.repository.UsuarioRepository
import com.application.aulatestes.data.repository.FakeUsuarioRepository
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Test

class UsuariosViewModelTest {

   private lateinit var usuariosViewModel: UsuariosViewModel
   private lateinit var usuarioRepository: UsuarioRepository


   @Before
   fun setUp() {
      usuarioRepository = FakeUsuarioRepository()
      usuariosViewModel = UsuariosViewModel( usuarioRepository )
   }

   @Test
   fun recuperarUsuarios() = runBlocking{

      val lista = usuariosViewModel.recuperarUsuarios()

      assertThat(
         lista
      ).isNotEmpty()

   }

   @After
   fun tearDown() {
   }
}