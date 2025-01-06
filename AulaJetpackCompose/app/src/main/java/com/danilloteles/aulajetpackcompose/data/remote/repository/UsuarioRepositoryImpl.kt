package com.danilloteles.aulajetpackcompose.data.remote.repository

import com.danilloteles.aulajetpackcompose.data.remote.api.DummyAPI
import com.danilloteles.aulajetpackcompose.data.remote.dto.User
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
   private val dummyAPI: DummyAPI
) : IUsuarioRepository{
   override suspend fun recuperarUsuarios(): List<User> {
      try {
         val resposta = dummyAPI.recuperarUsuarios()
         if ( resposta.isSuccessful && resposta.body() != null ) {
            val listaUsuarios = resposta.body()?.users
            if ( listaUsuarios != null ) {
               return listaUsuarios
            }
         }
      } catch (erroRequisicao: Exception) {
         erroRequisicao.printStackTrace()
      }
      return emptyList()
   }
}