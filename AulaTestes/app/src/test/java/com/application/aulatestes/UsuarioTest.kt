package com.application.aulatestes

import org.junit.Assert.*
import org.junit.Test

class UsuarioTest{

    @Test
    fun testCadastroUsuarioRetornaVerdadeiro(){

        //Dado
        val email = "jamilton@gmail.com"
        val senha = "1234@ja"

        //Quando
        val usuario = Usuario()
        val retorno = usuario.cadastrarUsuario(email, senha)

        //Então
        assertTrue(retorno)

    }
}