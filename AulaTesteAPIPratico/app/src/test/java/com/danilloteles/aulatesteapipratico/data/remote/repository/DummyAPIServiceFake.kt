package com.danilloteles.aulatesteapipratico.data.remote.repository

import com.danilloteles.aulatesteapipratico.data.remote.api.DummyAPIService
import com.danilloteles.aulatesteapipratico.data.remote.dto.ResultadoDummyAPI
import com.danilloteles.aulatesteapipratico.data.remote.dto.Usuario
import retrofit2.Response

class DummyAPIServiceFake : DummyAPIService {
   override suspend fun recuperarListaUsuarios(): Response<ResultadoDummyAPI> {
      return retrofit2.Response.success(
         ResultadoDummyAPI(
            20,
            10,
            100,
            listOf(
               Usuario(10, "j@gmail.com", "Jamilton", "M"),
               Usuario(23, "ana@gmail.com", "Ana", "F"),
               Usuario(45, "joao@gmail.com", "João", "M")
            )
         )
      )
   }
}