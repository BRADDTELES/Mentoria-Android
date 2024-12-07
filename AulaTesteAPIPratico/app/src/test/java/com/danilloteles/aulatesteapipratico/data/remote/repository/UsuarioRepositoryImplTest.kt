package com.danilloteles.aulatesteapipratico.data.remote.repository

import com.danilloteles.aulatesteapipratico.data.remote.api.DummyAPIService
import com.danilloteles.aulatesteapipratico.data.remote.dto.ResultadoDummyAPI
import com.danilloteles.aulatesteapipratico.data.remote.dto.Usuario
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import okhttp3.Response

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

//Mock vs Fake (DummyAPIService)
@RunWith(MockitoJUnitRunner::class)
class UsuarioRepositoryImplTest {

   @Mock
   private lateinit var dummyAPIService: DummyAPIService

   private lateinit var dummyAPIServiceFake: DummyAPIServiceFake

   @Before
   fun setUp() {
      dummyAPIServiceFake = DummyAPIServiceFake()
      MockitoAnnotations.openMocks(this)
   }

   @Test
   fun listar_recuperarListaUsuarios_retornaLista() = runTest() {

      val usuarioRepositoryImpl = UsuarioRepositoryImpl( dummyAPIServiceFake )
      val lista = usuarioRepositoryImpl.listar()

      assertThat(lista).isNotEmpty()

   }

   /*@Test
   fun listar_recuperarListaUsuarios_retornaLista() = runTest() {

      Mockito.`when`( dummyAPIService.recuperarListaUsuarios() ).thenReturn(
         retrofit2.Response.success(
            ResultadoDummyAPI(
               20,
               10,
               100,
               listOf(
                  Usuario(10, "j@gmail.com", "Jamilton", "M"),
                  Usuario(23, "ana@gmail.com", "Ana", "F"),
                  Usuario(45, "joao@gmail.com", "João", "M")
               )
            )
         )
      )

      val usuarioRepositoryImpl = UsuarioRepositoryImpl( dummyAPIService )
      val lista = usuarioRepositoryImpl.listar()

      assertThat(lista).isNotEmpty()

   }*/

   @After
   fun tearDown() {
   }
}