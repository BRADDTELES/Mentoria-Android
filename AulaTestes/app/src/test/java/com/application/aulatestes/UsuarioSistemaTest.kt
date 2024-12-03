package com.application.aulatestes

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UsuarioSistemaTest{

    @Before
    fun setUp() {}

    @After
    fun tearDown() {}

    @Test
    fun cadastrar_ValidarCadastroUsuario_RetornaVerdadeiro(){

        //Dado
        val email = "jamilton@gmail.com"
        val senha = "1234@ja"
        val senhaConfirmacao = "1234@ja"

        //Quando
        val usuario = UsuarioSistema()
        val retorno = usuario.cadastrarUsuario(email, senha, senhaConfirmacao)

        //Então
        assertTrue(retorno)

    }

    @Test
    fun cadastrar_ConfirmacaoSenha_RetornaTrue(){

        //Dado
        val usuario = UsuarioSistema()
        val email = "jamilton@gmail.com"
        val senha = "1234@ja"
        val senhaConfirmacao = "1234@ja"

        //Quando
        val retorno = usuario.cadastrarUsuario(email, senha, senhaConfirmacao)

        //Então
        assertTrue(retorno)

    }

    @Test
    fun calcular_DescontoSalario_RetornaTrue(){

        //Dado
        val usuario = UsuarioSistema()
        val salario = 1000.0
        val desconto = 100.0
        val esperado = 900.0

        //Quando
        val salarioCalculado = usuario.calcularDesconto(salario, desconto)
        val testeConfirmacao = salarioCalculado == esperado

        //Então
        assertTrue( testeConfirmacao )

    }
}