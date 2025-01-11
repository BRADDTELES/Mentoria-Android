package com.danilloteles.aulaifood.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.aulaifood.data.remote.firebase.repository.UploadRepository
import com.danilloteles.aulaifood.data.remote.firebase.repository.autenticacao.IAutenticacaoRepository
import com.danilloteles.aulaifood.domain.model.UploadStorage
import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.aulaifood.domain.usecase.AutenticacaoUseCase
import com.danilloteles.aulaifood.domain.usecase.ResultadoValidacao
import com.danilloteles.core.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutenticacaoViewModel @Inject constructor(
   private val autenticacaoUseCase: AutenticacaoUseCase,
   private val autenticacaoRepositoryImpl: IAutenticacaoRepository,
   private val uploadRepository: UploadRepository
): ViewModel() {

   private val _resultadoValidacao = MutableLiveData<ResultadoValidacao>()
   val resultadoValidacao: LiveData<ResultadoValidacao>
      get() = _resultadoValidacao

   private val _carregando = MutableLiveData<Boolean>()
   val carregando: LiveData<Boolean>
      get() = _carregando

   fun cadastrarUsuario( usuario: Usuario, uiStatus: (UIStatus<Boolean>) -> Unit  ) {

      val retornoValidacao = autenticacaoUseCase.validarCadastroUsuario( usuario )
      _resultadoValidacao.value = retornoValidacao
      if ( retornoValidacao.sucessoValidacaoCadastro ){
         _carregando.value = true
         viewModelScope.launch {
            autenticacaoRepositoryImpl.cadastrarUsuario(
               usuario, uiStatus
            )
            _carregando.postValue( false )
         }
      }
   }

   fun logarUsuario( usuario: Usuario, uiStatus: (UIStatus<Boolean>) -> Unit ) {
      val retornoValidacao = autenticacaoUseCase.validarLoginUsuario( usuario )
      _resultadoValidacao.value = retornoValidacao
      if ( retornoValidacao.sucessoValidacaoLogin ){
         _carregando.value = true
         viewModelScope.launch {
           autenticacaoRepositoryImpl.logarUsuario( usuario, uiStatus )
            _carregando.postValue( false )
         }
      }
   }

   fun verificarUsuarioLogado() : Boolean {
      return autenticacaoRepositoryImpl.verificarUsuarioLogado()
   }

   fun recuperarIdUsuarioLogado() : String {
      return autenticacaoRepositoryImpl.recuperarIdUsuarioLogado()
   }

   fun deslogarUsuario() {
      return autenticacaoRepositoryImpl.deslogarUsuario()
   }

   fun recuperarDadosUsuarioLogado(
      uiStatus: (UIStatus<Usuario>)->Unit
   ){
      uiStatus.invoke(UIStatus.Carregando)
      viewModelScope.launch {
         autenticacaoRepositoryImpl.recuperarDadosUsuarioLogado( uiStatus )
      }
   }

   fun atualizarUsuario(
      usuario: Usuario,
      uiStatus: (UIStatus<String>)->Unit
   ){
      uiStatus.invoke(UIStatus.Carregando)
      viewModelScope.launch {
         autenticacaoRepositoryImpl.atualizarUsuario( usuario, uiStatus )
      }
   }

   fun uploadImagem(
      uploadStorage: UploadStorage,
      uiStatus: (UIStatus<String>) -> Unit
   ) {
      uiStatus.invoke(UIStatus.Carregando)
      viewModelScope.launch {
         val upload = async {
            uploadRepository.upload(
               uploadStorage.local,
               uploadStorage.nomeImagem,
               uploadStorage.urlImagem
            )
         }
         val uiStatusUpload = upload.await()
         if (uiStatusUpload is UIStatus.Sucesso) {

            val urlImagem = uiStatusUpload.dados
            val usuario = Usuario( urlPerfil = urlImagem )
            autenticacaoRepositoryImpl.atualizarUsuario( usuario, uiStatus )
            uiStatus.invoke(UIStatus.Sucesso(""))

         } else {
            uiStatus.invoke(UIStatus.Erro("Erro ao fazer upload"))
         }
      }

   }

}