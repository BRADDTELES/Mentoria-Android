package com.danilloteles.aulatesteapipratico.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.danilloteles.aulatesteapipratico.data.remote.dto.Usuario
import com.danilloteles.aulatesteapipratico.domain.UsuarioUseCase
import com.danilloteles.aulatesteapipratico.util.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

//Fake vs Mock
@RunWith(MockitoJUnitRunner::class)
class UsuarioViewModelTest {

   @get:Rule
   val instantTaskExecutorRule = InstantTaskExecutorRule()

   @Mock
   private lateinit var usuarioUseCase: UsuarioUseCase

   private lateinit var usuarioViewModel: UsuarioViewModel

   @Before
   fun setUp() {
      MockitoAnnotations.openMocks(this)
      usuarioViewModel = UsuarioViewModel( usuarioUseCase )

   }

   @Test
   fun recuperarListaUsuarios_validarLiveData_retornaLiveData() = runTest {

      Mockito.`when`( usuarioUseCase() ).thenReturn(
         listOf(
            Usuario(10, "j@gmail.com", "Jamilton", "M"),
            Usuario(23, "ana@gmail.com", "Ana", "F"),
            Usuario(45, "joao@gmail.com", "João", "M")
         )
      )
      usuarioViewModel.recuperarListaUsuarios()//10 segundos
      val liveData = usuarioViewModel.listaUsuarios.getOrAwaitValue()

      assertThat( liveData ).isNotEmpty()

   }

   @After
   fun tearDown() {
   }
}