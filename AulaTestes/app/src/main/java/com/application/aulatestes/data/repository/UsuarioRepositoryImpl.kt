package com.application.aulatestes.data.repository

import com.application.aulatestes.data.model.Usuario
import kotlinx.coroutines.delay

class UsuarioRepositoryImpl : UsuarioRepository {

   override suspend fun salvar( usuario: Usuario ): Boolean {
      //código
      delay(2000)
      return true
   }

   override suspend fun listar(): List<Usuario> {
      return listOf(
         Usuario("Jamilton", "jamilton@gmail.com"),
         Usuario("Ana", "ana@gmail.com"),
         Usuario("Carlos", "carlos@gmail.com"),
      )
   }
}