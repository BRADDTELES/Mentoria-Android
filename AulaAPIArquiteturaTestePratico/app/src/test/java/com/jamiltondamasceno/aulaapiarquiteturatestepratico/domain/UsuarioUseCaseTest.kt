package com.jamiltondamasceno.aulaapiarquiteturatestepratico.domain

import com.google.common.truth.Truth.assertThat
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.dto.Usuario
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.repository.UsuarioRepositoryImpl
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith( MockitoJUnitRunner::class )
class UsuarioUseCaseTest {

    @Mock
    private lateinit var usuarioRepositoryImpl: UsuarioRepositoryImpl

    private lateinit var usuarioUseCase: UsuarioUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        usuarioUseCase = UsuarioUseCase( usuarioRepositoryImpl )
    }

    @Test
    fun invoke_recuperarListaUsuarios_retornaLista() = runTest {

        Mockito.`when`(
            usuarioRepositoryImpl.listar()
        ).thenReturn(
            listOf(
                Usuario(40, "j@gmail", "Jamilton", "M"),
                Usuario(46, "ana@gmail", "Ana", "F"),
                Usuario(42, "joao@gmail", "João", "M")
            )
        )

        val listaUsuariosFiltrados = usuarioUseCase()
        assertThat( listaUsuariosFiltrados ).isNotEmpty()

    }
}