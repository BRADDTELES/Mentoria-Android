package com.danilloteles.projetomvvmcleanhilt.data.repository

import com.danilloteles.projetomvvmcleanhilt.data.dto.toUsuario
import com.danilloteles.projetomvvmcleanhilt.data.remote.DummyAPI
import com.danilloteles.projetomvvmcleanhilt.domain.model.Usuario
import com.danilloteles.projetomvvmcleanhilt.domain.repository.UsuarioRepository
import javax.inject.Inject

// UsuarioRepositoryImplementacao é uma classe que implementa a interface UsuarioRepository
class UsuarioRepositoryImpl @Inject constructor(
    private val dummyAPI: DummyAPI
) : UsuarioRepository {
    override suspend fun resuperarUsuarios(): List<Usuario> {

        try {

            val resposta = dummyAPI.recuperarUsuarios()
            if ( resposta.isSuccessful && resposta.body() != null){
                val resultadoAPIDTO = resposta.body()
                val listaUsuarios = resultadoAPIDTO?.usuarioDTOS
                if ( listaUsuarios != null ){
                    /*val usuarios = listaUsuarios.map { usuarioDTO ->
                        usuarioDTO.toUsuario()
                    }
                    return usuarios*/
                    return listaUsuarios.map { it.toUsuario() }
                }
            }

        }catch (erroRecuperarUsuarios: Exception){
            erroRecuperarUsuarios.printStackTrace()
        }
        return emptyList()
    }
}