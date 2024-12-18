package com.danilloteles.aulatesteapipratico.domain

import com.danilloteles.aulatesteapipratico.data.remote.dto.Usuario
import com.danilloteles.aulatesteapipratico.data.remote.repository.IUsuarioRepository

class UsuarioUseCase(
    private val iUsuarioRepository: IUsuarioRepository
) {

    suspend operator fun invoke() : List<Usuario>{//getListUserCase
        val listaUsuarios = iUsuarioRepository.listar()
        return listaUsuarios.filter { it.age >= 40 }
    }

}