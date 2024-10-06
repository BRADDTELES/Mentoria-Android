package com.application.aprendendokotlindozero

class Usuario {

    //Propriedades
    fun logar( email: String, senha: String ){
        println("Usuário logado com email: $email e senha: $senha")
    }

    fun logar( numeroTelefone: String ){
        println("Usuário logado com telefone: $numeroTelefone")
    }
}

fun main() {

    val usuario = Usuario()
    usuario.logar("danillo@email.com", "123")
    usuario.logar("123456789")

}