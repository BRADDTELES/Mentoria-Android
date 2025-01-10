package com.danilloteles.loja.data.remote.firebase.repository

import com.danilloteles.loja.domain.model.Produto
import com.danilloteles.core.UIStatus

interface IProdutoRepository {
   suspend fun salvar(
      produto: Produto,
      uiStatus: (UIStatus<String>)-> Unit
   )
   suspend fun atualizar(
      produto: Produto,
      uiStatus: (UIStatus<String>)-> Unit
   )
}