package com.danilloteles.aulajetpackcompose.data.remote.repository

import com.danilloteles.aulajetpackcompose.data.remote.dto.User

interface IUsuarioRepository {
   suspend fun recuperarUsuarios() : List<User>
}