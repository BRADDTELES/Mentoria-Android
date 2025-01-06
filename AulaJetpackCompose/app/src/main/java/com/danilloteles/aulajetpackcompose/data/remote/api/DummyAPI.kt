package com.danilloteles.aulajetpackcompose.data.remote.api

import com.danilloteles.aulajetpackcompose.data.remote.dto.UsuarioResultado
import retrofit2.Response
import retrofit2.http.GET

interface DummyAPI {

   @GET("users")
   suspend fun recuperarUsuarios() : Response<UsuarioResultado>



}