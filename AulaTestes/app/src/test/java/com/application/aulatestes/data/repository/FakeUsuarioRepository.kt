package com.application.aulatestes.data.repository

import com.application.aulatestes.data.model.Usuario
import com.application.aulatestes.data.repository.UsuarioRepository

class FakeUsuarioRepository : UsuarioRepository {

    override suspend fun salvar(usuario: Usuario): Boolean {
        return true
    }

    override suspend fun listar(): List<Usuario> {
        return listOf(
            Usuario("Jamilton", "j@gmail.com"),
            Usuario("Ana", "ana@gmail.com"),
            Usuario("Pedro", "pedro@gmail.com"),
        )
    }
}