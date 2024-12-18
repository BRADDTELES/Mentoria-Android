package com.danilloteles.aulatesteapipratico.domain

import com.danilloteles.aulatesteapipratico.data.remote.dto.Usuario
import com.danilloteles.aulatesteapipratico.data.remote.repository.UsuarioRepositoryImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

//Mock vs Fake (UsuarioRepositoryImpl)
@RunWith(MockitoJUnitRunner::class)
class UsuarioUseCaseTest {

   @Mock
   private lateinit var usuarioRepositoryImpl: UsuarioRepositoryImpl

   private lateinit var usuarioRepositoryFake: UsuarioRepositoryFake

   private lateinit var usuarioUseCase: UsuarioUseCase

   @Before
   fun setUp() {
      MockitoAnnotations.openMocks(this)
      //Mock
      //usuarioUseCase = UsuarioUseCase( usuarioRepositoryImpl )
      //Fake
      usuarioRepositoryFake = UsuarioRepositoryFake()
      usuarioUseCase = UsuarioUseCase( usuarioRepositoryFake )
   }

   @Test
   fun invoke_retornaUsuariosFiltrados_retornaLista() = runTest() {

      val listaUsuariosFiltrados = usuarioUseCase()
      assertThat( listaUsuariosFiltrados ).isNotEmpty()

   }

   /*@Test
   fun invoke_retornaUsuariosFiltrados_retornaLista() = runTest() {

      Mockito.`when`( usuarioRepositoryImpl.listar() ).thenReturn(
         listOf(
            Usuario(42, "j@gmail.com", "Jamilton", "M"),
            Usuario(23, "ana@gmail.com", "Ana", "F"),
            Usuario(45, "joao@gmail.com", "João", "M")
         )
      )

      val listaUsuariosFiltrados = usuarioUseCase()
      assertThat( listaUsuariosFiltrados ).isNotEmpty()

   }*/

   @After
   fun tearDown() {
   }
}