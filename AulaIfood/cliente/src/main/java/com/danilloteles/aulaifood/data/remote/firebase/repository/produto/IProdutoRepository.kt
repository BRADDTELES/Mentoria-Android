package com.danilloteles.aulaifood.data.remote.firebase.repository.produto

import com.danilloteles.aulaifood.domain.model.Produto
import com.danilloteles.core.UIStatus

interface IProdutoRepository {

   suspend fun listar(
      idLoja: String,
      uiStatus: (UIStatus<List<Produto>>)-> Unit
   )

   suspend fun recuperarProdutoPeloId(
      idLoja: String,
      idProduto: String,
      uiStatus: (UIStatus<Produto>)-> Unit
   )

}