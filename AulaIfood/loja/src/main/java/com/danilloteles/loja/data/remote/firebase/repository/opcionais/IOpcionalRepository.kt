package com.danilloteles.loja.data.remote.firebase.repository.opcionais

import com.danilloteles.core.UIStatus
import com.danilloteles.loja.domain.model.Opcional

interface IOpcionalRepository {
   suspend fun salvar(
      opcional: Opcional,
      uiStatus: (UIStatus<String>)-> Unit
   )
   suspend fun listar(
      idProduto: String,
      uiStatus: (UIStatus<List<Opcional>>)-> Unit
   )
   suspend fun remover(
      opcional: Opcional,
      uiStatus: (UIStatus<Boolean>)-> Unit
   )
}