package com.application.aulatestes

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class OperacoesMatematicasTest {

    private lateinit var operacoesMatematicas: OperacoesMatematicas

    @Before
    fun setUp() {
        operacoesMatematicas = OperacoesMatematicas()
    }

    @After
    fun tearDown() {}

    @Test
    fun somar_calculaSomaEntreDoisNumeros_retornaVerdadeiro() {

        //Dado (simulado)
        val numero1 = 10
        val numero2 = 10
        val numeroEsperado = 20

        //Quando
        val numeroRotornado = operacoesMatematicas.somar(numero1, numero2)
        //val resultado = numeroEsperado == numeroRotornado

        //Então
        //assertTrue( resultado )
        //assertEquals(numeroEsperado, numeroRotornado)
        //assertEquals(10.95, 10.93, 0.01)//0.02
        //assertEquals("Números diferentes",10, 20)
        //assertNotEquals(10,30)
        //assertTrue(true)
        //assertFalse("Deveria retornar false",false)
        //assertSame( operacoesMatematicas, OperacoesMatematicas() )
        val condicao = 1 != 2
        if (condicao){
            fail("Numero1 é diferente de numero 2")
        }

    }

    fun apoio(){}
}