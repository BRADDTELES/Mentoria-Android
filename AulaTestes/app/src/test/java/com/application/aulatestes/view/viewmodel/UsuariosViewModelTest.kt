package com.application.aulatestes.view.viewmodel

import com.application.aulatestes.data.model.Usuario
import com.google.common.truth.Truth.assertThat
import com.application.aulatestes.data.repository.UsuarioRepository
import com.application.aulatestes.data.repository.FakeUsuarioRepository
import com.application.aulatestes.data.repository.UsuarioRepositoryImpl
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith( MockitoJUnitRunner::class )
class UsuariosViewModelTest {

   private lateinit var usuariosViewModel: UsuariosViewModel

   @Mock
   //private lateinit var mockUsuarioRepository: UsuarioRepository
   private lateinit var mockUsuarioRepository: UsuarioRepositoryImpl


   @Before
   fun setUp() {
      //usuarioRepository = FakeUsuarioRepository()
      //usuarioRepository = mock( UsuarioRepository::class.java )

      MockitoAnnotations.openMocks(this)
   }

   @Test
   fun recuperarUsuarios() = runTest(){

      usuariosViewModel = UsuariosViewModel( mockUsuarioRepository )
      Mockito.`when`( mockUsuarioRepository.listar() ).thenReturn(
         listOf(
            Usuario("jamilton", "j@gmail.com")
         )
         //emptyList()
      )//Dados Ficticios
      /*Mockito.`when`( mockUsuarioRepository.listar() )
         .thenCallRealMethod()//Dados Reais
       */

      val lista = usuariosViewModel.recuperarUsuarios()

      assertThat(
         lista
      ).isNotEmpty()

   }

   @After
   fun tearDown() {
   }
}