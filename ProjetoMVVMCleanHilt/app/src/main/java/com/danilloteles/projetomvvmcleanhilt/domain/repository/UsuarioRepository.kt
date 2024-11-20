package com.danilloteles.projetomvvmcleanhilt.domain.repository

import com.danilloteles.projetomvvmcleanhilt.domain.model.Usuario

interface UsuarioRepository {
    suspend fun resuperarUsuarios() : List<Usuario>
}