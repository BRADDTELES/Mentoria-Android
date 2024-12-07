package com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.repository

import com.google.common.truth.Truth.assertThat
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.api.DummyAPIService
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.dto.ResultadoDummyAPI
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.dto.Usuario
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

@RunWith( MockitoJUnitRunner::class )
class UsuarioRepositoryImplTest {

    @Mock
    private lateinit var dummyAPIService: DummyAPIService

    private lateinit var usuarioRepositoryImpl: UsuarioRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        usuarioRepositoryImpl = UsuarioRepositoryImpl( dummyAPIService )
    }

    @Test
    fun listar() = runTest{
        //dummyAPI.recupearListaUsuarios()
        Mockito.`when`(
            dummyAPIService.recupearListaUsuarios()
        ).thenReturn(//Response<ResultadoDummyAPI>
            retrofit2.Response.success(
                ResultadoDummyAPI(
                    20,
                    10,
                    100,
                    listOf(
                        Usuario(35, "j@gmail", "Jamilton", "M"),
                        Usuario(46, "ana@gmail", "Ana", "F"),
                        Usuario(42, "joao@gmail", "João", "M")
                    )
                )
            )
        )
        val listaUsuarios = usuarioRepositoryImpl.listar()
        assertThat( listaUsuarios ).isNotEmpty()

    }

    @After
    fun tearDown() {
    }
}