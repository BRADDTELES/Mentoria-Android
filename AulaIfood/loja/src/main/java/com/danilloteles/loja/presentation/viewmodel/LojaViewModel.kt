package com.danilloteles.loja.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.core.UIStatus
import com.danilloteles.loja.data.remote.firebase.repository.UploadRepository
import com.danilloteles.loja.domain.model.UploadLoja
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LojaViewModel @Inject constructor(
   private val uploadRepository: UploadRepository
) : ViewModel() {

   fun uploadImagem(
      uploadLoja: UploadLoja,
      uiStatus: (UIStatus<Boolean>) -> Unit
   ) {
      uiStatus.invoke( UIStatus.Carregando )
      viewModelScope.launch {
         val upload = async {
            uploadRepository.upload(
               uploadLoja.local, uploadLoja.nomeImagem, uploadLoja.urlImagem
            )
         }
         val uiStatusUpload = upload.await()
         if ( uiStatusUpload is UIStatus.Sucesso ) {
            val urlImagem = uiStatusUpload.dados
            uiStatus.invoke( UIStatus.Sucesso( true ) )
         }else{
            uiStatus.invoke( UIStatus.Erro("Erro ao fazer upload") )
         }
      }

   }

}