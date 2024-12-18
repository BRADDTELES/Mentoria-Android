package com.application.aulaprojetoarquiteturas.model.api

import com.application.aulaprojetoarquiteturas.model.Usuario

class UsuarioAPI {

    fun recuperarUsuario() : List<Usuario>{
        val listaUsuario =  listOf(
            Usuario("Jamilton", 35),
            Usuario("Pedro", 20),
            Usuario("Maria", 56),
            Usuario("Ana", 18)
        )
        return listaUsuario
    }

}