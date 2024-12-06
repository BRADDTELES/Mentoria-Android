package com.danilloteles.aulatesteapipratico.data.remote.repository

import com.danilloteles.aulatesteapipratico.data.remote.dto.Usuario

interface IUsuarioRepository {
    suspend fun listar(): List<Usuario>
}