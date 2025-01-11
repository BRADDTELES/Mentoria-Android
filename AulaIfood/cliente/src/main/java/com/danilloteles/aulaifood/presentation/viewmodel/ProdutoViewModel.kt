package com.danilloteles.aulaifood.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.aulaifood.data.remote.firebase.repository.produto.IProdutoRepository
import com.danilloteles.aulaifood.domain.model.Opcional
import com.danilloteles.aulaifood.domain.model.Produto
import com.danilloteles.aulaifood.domain.model.ProdutosSeparados
import com.danilloteles.aulaifood.domain.usecase.produto.RecuperarProdutosPorTipoUseCase
import com.danilloteles.core.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProdutoViewModel @Inject constructor(
   private val produtoRepositoryImpl: IProdutoRepository,
   private val recuperarProdutosPorTipoUseCase: RecuperarProdutosPorTipoUseCase
) : ViewModel() {

   fun recuperProdutoPeloId(
      idLoja: String,
      idProduto: String,
      uiStatus: (UIStatus<Produto>) -> Unit
   ){
      //uiStatus.invoke( UIStatus.Carregando )//NÃO PRESTOU o carregamento pelo o Id do produto
      viewModelScope.launch {
         produtoRepositoryImpl.recuperarProdutoPeloId( idLoja, idProduto, uiStatus )
      }
   }

   fun listarOpcionais(
      idLoja: String,
      idProduto: String,
      uiStatus: (UIStatus<List<Opcional>> ) -> Unit
   ){
      uiStatus.invoke( UIStatus.Carregando )
      viewModelScope.launch {
         produtoRepositoryImpl.listarOpcionais(
            idLoja, idProduto, uiStatus
         )
      }
   }

   fun listar(
      idLoja: String,
      uiStatus: (UIStatus<List<ProdutosSeparados>> ) -> Unit
   ){
      //uiStatus.invoke( UIStatus.Carregando )
      viewModelScope.launch {
         recuperarProdutosPorTipoUseCase( idLoja, uiStatus )
      }
   }

}