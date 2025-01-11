package com.danilloteles.aulaifood.data.remote.firebase.repository.loja

import com.danilloteles.aulaifood.domain.model.Loja
import com.danilloteles.core.UIStatus

interface ILojaRepository {

   suspend fun recuperarDadosLoja(
      idLoja: String,
      uiStatus: (UIStatus<Loja>)-> Unit
   )

   suspend fun listar(
      uiStatus: (UIStatus<List<Loja>>)-> Unit
   )

}