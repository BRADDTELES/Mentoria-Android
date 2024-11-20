package com.danilloteles.projetomvvmcleanhilt.data.repository

import com.danilloteles.projetomvvmcleanhilt.data.remote.DummyAPI
import com.danilloteles.projetomvvmcleanhilt.domain.model.Usuario
import com.danilloteles.projetomvvmcleanhilt.domain.repository.UsuarioRepository
import javax.inject.Inject

// UsuarioRepositoryImplementacao é uma classe que implementa a interface UsuarioRepository
class UsuarioRepositoryImpl @Inject constructor(
    val dummyAPI: DummyAPI
) : UsuarioRepository {
    override suspend fun resuperarUsuarios(): List<Usuario> {
        return emptyList()
    }
}