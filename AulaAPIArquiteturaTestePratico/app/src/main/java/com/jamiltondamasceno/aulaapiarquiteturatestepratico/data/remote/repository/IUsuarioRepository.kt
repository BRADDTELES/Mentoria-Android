package com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.repository

import com.jamiltondamasceno.aulaapiarquiteturatestepratico.data.remote.dto.Usuario

interface IUsuarioRepository {
    suspend fun listar() : List<Usuario>
}