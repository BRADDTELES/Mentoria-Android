package com.application.aulatestes

import org.junit.Assert.*
import org.junit.Test

class UsuarioTest{

    @Test
    fun validarCadastroUsuarioRetornaVerdadeiro(){

        //Dado
        val email = "jamilton@gmail.com"
        val senha = "1234@ja"
        val senhaConfirmacao = "1234@ja"

        //Quando
        val usuario = Usuario()
        val retorno = usuario.cadastrarUsuario(email, senha, senhaConfirmacao)

        //Então
        assertTrue(retorno)

    }

    @Test
    fun validarConfirmacaoSenhaRetornaTrue(){

        //Dado
        val usuario = Usuario()
        val email = "jamilton@gmail.com"
        val senha = "1234@ja"
        val senhaConfirmacao = "1234@ja"

        //Quando
        val retorno = usuario.cadastrarUsuario(email, senha, senhaConfirmacao)

        //Então
        assertTrue(retorno)

    }

    @Test
    fun calcularDescontoSalarioRetornaTrue(){

        //Dado
        val usuario = Usuario()
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