package com.danilloteles.loja.data.remote.firebase.repository

import com.danilloteles.core.UIStatus

interface ILojaRepository {
   suspend fun atualizarCampo(
      campo: Map<String, Any>,//mapof("categoria" to "")
      uiStatus: (UIStatus<Boolean>)-> Unit
   )
}