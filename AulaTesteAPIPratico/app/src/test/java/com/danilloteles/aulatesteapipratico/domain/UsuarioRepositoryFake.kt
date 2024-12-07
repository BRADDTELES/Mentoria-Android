package com.danilloteles.aulatesteapipratico.domain

import com.danilloteles.aulatesteapipratico.data.remote.dto.Usuario
import com.danilloteles.aulatesteapipratico.data.remote.repository.IUsuarioRepository

class UsuarioRepositoryFake : IUsuarioRepository {
   override suspend fun listar(): List<Usuario> {
      return listOf(
         Usuario(42, "j@gmail.com", "Jamilton", "M"),
         Usuario(23, "ana@gmail.com", "Ana", "F"),
         Usuario(45, "joao@gmail.com", "João", "M")
      )
   }
}