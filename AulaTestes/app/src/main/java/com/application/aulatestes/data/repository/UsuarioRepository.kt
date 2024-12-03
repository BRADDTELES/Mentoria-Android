package com.application.aulatestes.data.repository

import com.application.aulatestes.data.model.Usuario

interface UsuarioRepository {
   fun salvar( usuario: Usuario ): Boolean
   fun listar(): List<Usuario>
}