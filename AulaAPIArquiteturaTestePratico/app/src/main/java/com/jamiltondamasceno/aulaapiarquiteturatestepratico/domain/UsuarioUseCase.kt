package com.jamiltondamasceno.aulaapiarquiteturatestepratico.domain

import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.dto.Usuario
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.repository.IUsuarioRepository

class UsuarioUseCase(
    private val iUsuarioRepository: IUsuarioRepository
) {

    suspend operator fun invoke() : List<Usuario> {//getListUserUseCase
        val listaUsuarios = iUsuarioRepository.listar()
        return listaUsuarios.filter { it.age >= 40 }//usuarios >= 40
    }

}