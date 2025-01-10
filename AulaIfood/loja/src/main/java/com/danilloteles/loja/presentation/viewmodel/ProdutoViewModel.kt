package com.danilloteles.loja.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.core.UIStatus
import com.danilloteles.loja.data.remote.firebase.repository.IProdutoRepository
import com.danilloteles.loja.data.remote.firebase.repository.UploadRepository
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

   fun uploadImagem(
      uploadStorage: UploadStorage,
      uiStatus: (UIStatus<Boolean>) -> Unit
   ) {
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
            //Salvar os dados do produto
         }else{
            uiStatus.invoke( UIStatus.Erro("Erro ao fazer upload") )
         }
      }

   }

}