package com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.repository

import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.api.DummyAPIService
import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.dto.Usuario

class UsuarioRepositoryImpl(
    private val dummyAPI: DummyAPIService
) : IUsuarioRepository {
    override suspend fun listar(): List<Usuario> {

        val resposta = dummyAPI.recupearListaUsuarios()//5 segundos
        if( resposta.isSuccessful && resposta.body() != null ){
            val listaUsuarios = resposta.body()?.users
            if( listaUsuarios != null ){
                return listaUsuarios
                //return emptyList()
            }
        }

        return emptyList()

    }
}