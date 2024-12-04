package com.application.aulatestes.data.repository

import com.application.aulatestes.data.model.Usuario
import kotlinx.coroutines.delay

class UsuarioRepositoryImpl : UsuarioRepository {

   override suspend fun salvar( usuario: Usuario ): Boolean {
      //código
      delay(2000)
      return true
   }

   override suspend fun listar(): List<Usuario> {

      /*return listOf(
         Usuario("Jamilton", "jamilton@gmail.com"),
         Usuario("Ana", "ana@gmail.com"),
         Usuario("Carlos", "carlos@gmail.com"),
      )*/

      /* CÓDIGO REAL

        val listaResposta = api.getUsuarios()
        if ( listaResposta.isSuccessful ){
            val listaUsuariosDto = listaResposta.body()
            if( listaUsuariosDto != null ){// List<UsuarioDTO>
                /*val listaUsuarios = listaUsuariosDto.map { usuarioDTO ->
                    usuarioDTO.toUsuario()
                }*/
                return listaUsuariosDto.map {it.toUsuario()}
            }
        }
         */

      return emptyList()

   }
}