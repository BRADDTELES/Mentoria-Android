package com.danilloteles.loja.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.core.UIStatus
import com.danilloteles.loja.data.remote.firebase.repository.opcionais.IOpcionalRepository
import com.danilloteles.loja.data.remote.firebase.repository.UploadRepository
import com.danilloteles.loja.domain.model.Opcional
import com.danilloteles.loja.domain.model.UploadStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OpcionalViewModel@Inject constructor(
   private val uploadRepository: UploadRepository,
   private val opcionalRepositoryImpl: IOpcionalRepository
) : ViewModel() {

   fun remover(
      opcional: Opcional,
      uiStatus: (UIStatus<Boolean>) -> Unit
   ){
      uiStatus.invoke( UIStatus.Carregando )
      viewModelScope.launch {
         opcionalRepositoryImpl.remover( opcional, uiStatus )
      }
   }

   fun listar(
      idProduto: String,
      uiStatus: (UIStatus<List<Opcional>> ) -> Unit
   ){
      uiStatus.invoke( UIStatus.Carregando )
      viewModelScope.launch {
         opcionalRepositoryImpl.listar( idProduto, uiStatus )
      }
   }

   fun salvar(
      uploadStorage: UploadStorage,
      opcional: Opcional,
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
            opcional.url = urlImagem
            opcionalRepositoryImpl.salvar( opcional, uiStatus )
         }else{
            uiStatus.invoke( UIStatus.Erro("Erro ao fazer upload") )
         }
      }

   }

}