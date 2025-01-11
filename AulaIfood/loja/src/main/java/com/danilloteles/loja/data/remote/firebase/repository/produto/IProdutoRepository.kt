package com.danilloteles.loja.data.remote.firebase.repository.produto

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
   suspend fun listar(
      uiStatus: (UIStatus<List<Produto>>)-> Unit
   )

   suspend fun recuperarProdutoPeloId(
      idProduto: String,
      uiStatus: (UIStatus<Produto>)-> Unit
   )

   suspend fun remover(
      idProduto: String,
      uiStatus: (UIStatus<Boolean>)-> Unit
   )
}