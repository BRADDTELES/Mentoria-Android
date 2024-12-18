package com.danilloteles.aulatesteapipratico.data.remote.repository

import com.danilloteles.aulatesteapipratico.data.remote.api.DummyAPIService
import com.danilloteles.aulatesteapipratico.data.remote.dto.Usuario

class UsuarioRepositoryImpl(
    private val dummyAPI: DummyAPIService
) : IUsuarioRepository {
    override suspend fun listar(): List<Usuario> {

        val resposta = dummyAPI.recuperarListaUsuarios()
        if (resposta.isSuccessful && resposta.body() != null){
            val listaUsuarios = resposta.body()?.users
            if (listaUsuarios != null){
                return listaUsuarios
            }
        }
        return emptyList()
    }
}