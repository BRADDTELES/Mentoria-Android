package com.danilloteles.projetomvvmcleanhilt.data.repository

import android.util.Log
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
                val listaUsuarios = resultadoAPIDTO?.usuariosDTO
                if ( listaUsuarios != null ){
                    /*val usuarios = listaUsuarios.map { usuarioDTO ->
                        usuarioDTO.toUsuario()
                    }
                    usuarios.forEach { user ->
                        Log.i("lista_usuarios", "Nome: ${user.nome} ${user.sobrenome}, ${user.idade} anos" +
                                "- Endereço: ${user.endereco} " +
                                "- Email: ${user.email} " +
                                "- Contato: ${user.telefone} " +
                                "- Imagem: ${user.imagem} \n")
                    }
                    return usuarios*/
                    return listaUsuarios.map { it.toUsuario() }
                }
            }else{
                Log.i("lista_usuarios", "${resposta.message()}")
            }

        }catch (erroRecuperarUsuarios: Exception){
            erroRecuperarUsuarios.printStackTrace()
        }
        return emptyList()
    }
}