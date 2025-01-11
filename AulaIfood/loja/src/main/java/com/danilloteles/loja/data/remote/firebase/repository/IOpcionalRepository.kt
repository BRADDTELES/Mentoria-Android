package com.danilloteles.loja.data.remote.firebase.repository

import com.danilloteles.core.UIStatus
import com.danilloteles.loja.domain.model.Opcional
import com.danilloteles.loja.domain.model.Produto

interface IOpcionalRepository {
   suspend fun salvar(
      opcional: Opcional,
      uiStatus: (UIStatus<String>)-> Unit
   )
   suspend fun listar(
      uiStatus: (UIStatus<List<Opcional>>)-> Unit
   )
   suspend fun remover(
      opcional: Opcional,
      uiStatus: (UIStatus<Boolean>)-> Unit
   )
}