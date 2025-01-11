package com.danilloteles.aulaifood.data.remote.firebase.repository.autenticacao

import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.core.UIStatus

interface IAutenticacaoRepository {
   suspend fun cadastrarUsuario(
      usuario: Usuario,
      uiStatus: (UIStatus<Boolean>) -> Unit
   )
   suspend fun logarUsuario(
      usuario: Usuario,
      uiStatus: (UIStatus<Boolean>) -> Unit
   )
   fun verificarUsuarioLogado() : Boolean
   fun recuperarIdUsuarioLogado(): String
}