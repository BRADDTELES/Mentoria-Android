package com.application.aulatestes.view.viewmodel

import com.application.aulatestes.data.model.Usuario
import com.application.aulatestes.data.repository.UsuarioRepository

class UsuariosViewModel(
   private val usuarioRepository: UsuarioRepository
) {

   suspend fun recuperarUsuarios() : List<Usuario> {
      //Livedata
      //Fluxo de execução
      return usuarioRepository.listar()
   }

}