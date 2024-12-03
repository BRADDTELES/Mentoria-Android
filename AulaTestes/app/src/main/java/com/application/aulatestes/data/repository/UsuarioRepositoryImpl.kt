package com.application.aulatestes.data.repository

import com.application.aulatestes.data.model.Usuario

class UsuarioRepositoryImpl : UsuarioRepository {
   override fun salvar( usuario: Usuario ): Boolean {
      //código
      return true
   }

   override fun listar(): List<Usuario> {
      return listOf(
         Usuario("Jamilton", "jamilton@gmail.com"),
         Usuario("Ana", "ana@gmail.com"),
         Usuario("Carlos", "carlos@gmail.com"),
      )
   }
}