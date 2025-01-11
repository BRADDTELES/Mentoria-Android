package com.danilloteles.loja.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.core.UIStatus
import com.danilloteles.loja.data.remote.firebase.repository.IProdutoRepository
import com.danilloteles.loja.data.remote.firebase.repository.UploadRepository
import com.danilloteles.loja.domain.model.Produto
import com.danilloteles.loja.domain.model.UploadStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProdutoViewModel @Inject constructor(
   private val uploadRepository: UploadRepository,
   private val produtoRepositoryImpl: IProdutoRepository
) : ViewModel() {

   fun remover(
      idProduto: String,
      uiStatus: (UIStatus<Boolean>) -> Unit
   ){
      uiStatus.invoke( UIStatus.Carregando )
      viewModelScope.launch {
         produtoRepositoryImpl.remover( idProduto, uiStatus )
      }
   }

   fun recuperProdutoPeloId(
      idProduto: String,
      uiStatus: (UIStatus<Produto>) -> Unit
   ){
      //uiStatus.invoke( UIStatus.Carregando )//NÃO PRESTOU o carregamento pelo o Id do produto
      viewModelScope.launch {
         produtoRepositoryImpl.recuperarProdutoPeloId( idProduto, uiStatus )
      }
   }

   fun listar( uiStatus: (UIStatus<List<Produto>> ) -> Unit ){
      uiStatus.invoke( UIStatus.Carregando )
      viewModelScope.launch {
         produtoRepositoryImpl.listar( uiStatus )
      }
   }

   fun salvar(
      produto: Produto,
      uiStatus: (UIStatus<String>) -> Unit
   ){
      uiStatus.invoke( UIStatus.Carregando )
      viewModelScope.launch {
         if ( produto.id.isEmpty() ) {//Salvar
            produtoRepositoryImpl.salvar( produto, uiStatus )
         }else{//Atualizar
            produtoRepositoryImpl.atualizar( produto, uiStatus )
         }
      }
   }

   fun uploadImagem(
      uploadStorage: UploadStorage,
      idProduto: String,
      uiStatus: (UIStatus<String>) -> Unit
   ){
      uiStatus.invoke( UIStatus.Carregando )
      viewModelScope.launch {
         val upload = async {
            uploadRepository.upload(
               uploadStorage.local, uploadStorage.nomeImagem, uploadStorage.urlImagem
            )
         }
         val uiStatusUpload = upload.await()
         if ( uiStatusUpload is UIStatus.Sucesso ) {

            val urlImagem = uiStatusUpload.dados
            val produto = Produto(
               id = idProduto, url = urlImagem
            )

            if ( idProduto.isEmpty() ) {//Salvar
               produtoRepositoryImpl.salvar( produto, uiStatus )
            }else{//Atualizar
               produtoRepositoryImpl.atualizar( produto, uiStatus )
            }
         }else{
            uiStatus.invoke( UIStatus.Erro("Erro ao fazer upload") )
         }
      }

   }

}