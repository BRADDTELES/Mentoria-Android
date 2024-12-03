package com.application.aulatestes.data.repository

import com.application.aulatestes.data.model.Usuario

interface UsuarioRepository {
   suspend fun salvar( usuario: Usuario ): Boolean
   suspend fun listar(): List<Usuario>
}