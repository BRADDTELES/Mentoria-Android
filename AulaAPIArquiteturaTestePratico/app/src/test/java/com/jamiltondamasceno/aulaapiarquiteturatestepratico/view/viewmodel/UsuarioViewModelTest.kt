package com.jamiltondamasceno.aulaapiarquiteturatestepratico.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.dto.Usuario
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.domain.UsuarioUseCase
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.util.getOrAwaitValue
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith( MockitoJUnitRunner::class )
class UsuarioViewModelTest {

    @get:Rule//Mudar a regra de Execução -> Assícrona para Síncrona
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
    fun recuperarListaUsuarios() = runTest {

        Mockito.`when`(
            usuarioUseCase()
        ).thenReturn(
            listOf(
                Usuario(40, "j@gmail", "Jamilton", "M"),
                Usuario(46, "ana@gmail", "Ana", "F"),
                Usuario(42, "joao@gmail", "João", "M")
            )
        )

        usuarioViewModel.recuperarListaUsuarios()
        val listaUsuarios = usuarioViewModel.listaUsuarios.getOrAwaitValue()
        assertThat( listaUsuarios ).isNotEmpty()

    }
}