package com.danilloteles.loja.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.core.UIStatus
import com.danilloteles.loja.data.remote.firebase.repository.ILojaRepository
import com.danilloteles.loja.data.remote.firebase.repository.UploadRepository
import com.danilloteles.loja.domain.model.Categoria
import com.danilloteles.loja.domain.model.Loja
import com.danilloteles.loja.domain.model.UploadLoja
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LojaViewModel @Inject constructor(
   private val uploadRepository: UploadRepository,
   private val lojaRepositoryImpl: ILojaRepository
) : ViewModel() {

   fun atualizarLoja( loja: Loja, uiStatus: (UIStatus<Boolean>) -> Unit ){
      uiStatus.invoke(UIStatus.Carregando)
      viewModelScope.launch {
         lojaRepositoryImpl.atualizarLoja( loja, uiStatus )
      }
   }

   //CategoriaViewModel, CategoriasRepositoryImpl, ICategoriasRepository, Categorias
   fun recuperarCategorias( uiStatus: (UIStatus<List<Categoria>>) -> Unit ){
      viewModelScope.launch {
         lojaRepositoryImpl.recuperarCategorias( uiStatus )
      }
   }

   fun recuperarDadosLoja( uiStatus: (UIStatus<Loja>) -> Unit ){
      uiStatus.invoke(UIStatus.Carregando)
      viewModelScope.launch {
         lojaRepositoryImpl.recuperarDadosLoja( uiStatus )
      }
   }

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
            val campo: Map<String, Any>
            if ( uploadLoja.nomeImagem == "imagem_perfil" ) {
               campo = mapOf("urlPerfil" to urlImagem)
            }else{
               campo = mapOf("urlCapa" to urlImagem)
            }
            lojaRepositoryImpl.atualizarCampo(campo, uiStatus)
            //uiStatus.invoke( UIStatus.Sucesso( true ) )
         }else{
            uiStatus.invoke( UIStatus.Erro("Erro ao fazer upload") )
         }
      }

   }

}