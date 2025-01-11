package com.danilloteles.aulaifood.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.aulaifood.data.remote.firebase.repository.loja.ILojaRepository
import com.danilloteles.aulaifood.domain.model.Loja
import com.danilloteles.core.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LojaViewModel @Inject constructor(
   private val lojaRepositoryImpl: ILojaRepository
) : ViewModel() {

   fun listar(uiStatus: (UIStatus<List<Loja>>) -> Unit ){
      viewModelScope.launch {
         lojaRepositoryImpl.listar( uiStatus )
      }
   }

   fun recuperarDadosLoja(
      idLoja: String,
      uiStatus: (UIStatus<Loja>) -> Unit
   ){
      uiStatus.invoke(UIStatus.Carregando)
      viewModelScope.launch {
         lojaRepositoryImpl.recuperarDadosLoja( idLoja, uiStatus )
      }
   }

}