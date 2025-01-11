package com.danilloteles.loja.data.remote.firebase.repository.loja

import com.danilloteles.core.UIStatus
import com.danilloteles.loja.domain.model.Categoria
import com.danilloteles.loja.domain.model.Loja

interface ILojaRepository {
   suspend fun atualizarCampo(
      campo: Map<String, Any>,//mapof("categoria" to "")
      uiStatus: (UIStatus<Boolean>)-> Unit
   )
   suspend fun recuperarDadosLoja(uiStatus: (UIStatus<Loja>)-> Unit)
   suspend fun recuperarCategorias(uiStatus: (UIStatus<List<Categoria>>)-> Unit)
   suspend fun atualizarLoja(
      loja: Loja,
      uiStatus: (UIStatus<Boolean>)-> Unit
   )
}