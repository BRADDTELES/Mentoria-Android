package com.danilloteles.loja.data.remote.firebase.repository.autenticacao

import com.danilloteles.core.UIStatus
import com.danilloteles.loja.domain.model.Usuario

interface IAutenticacaoRepository {
   suspend fun cadastrarUsuario(
      usuario: Usuario,
      uiStatus: (UIStatus<Boolean>) -> Unit
   )

   suspend fun logarUsuario(
      usuario: Usuario,
      uiStatus: (UIStatus<Boolean>) -> Unit
   )

   fun verificarUsuarioLogado(): Boolean
}