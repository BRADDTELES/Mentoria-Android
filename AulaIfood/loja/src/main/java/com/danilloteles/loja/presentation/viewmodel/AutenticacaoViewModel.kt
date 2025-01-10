package com.danilloteles.loja.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.aulaifood.domain.usecase.ResultadoValidacao
import com.danilloteles.core.UIStatus
import com.danilloteles.loja.data.remote.firebase.repository.IAutenticacaoRepository
import com.danilloteles.loja.domain.model.Usuario
import com.danilloteles.loja.domain.usecase.AutenticacaoUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutenticacaoViewModel @Inject constructor(
   private val autenticacaoUseCase: AutenticacaoUseCase,
   private val autenticacaoRepositoryImpl: IAutenticacaoRepository,
   private val firebaseAuth: FirebaseAuth
) : ViewModel() {

   private val _resultadoValidacao = MutableLiveData<ResultadoValidacao>()
   val resultadoValidacao: LiveData<ResultadoValidacao>
      get() = _resultadoValidacao

   private val _carregando = MutableLiveData<Boolean>()
   val carregando: LiveData<Boolean>
      get() = _carregando

   fun cadastrarUsuario(usuario: Usuario, uiStatus: (UIStatus<Boolean>) -> Unit) {

      val retornoValidacao = autenticacaoUseCase.validarCadastroUsuario(usuario)
      _resultadoValidacao.postValue(retornoValidacao)
      if (retornoValidacao.sucessoValidacaoCadastro) {
         _carregando.value = true
         viewModelScope.launch {
            autenticacaoRepositoryImpl.cadastrarUsuario(usuario, uiStatus)
            _carregando.postValue(false)
         }
      }
   }

   fun logarUsuario(usuario: Usuario, uiStatus: (UIStatus<Boolean>) -> Unit) {
      val retornoValidacao = autenticacaoUseCase.validarLoginUsuario(usuario)
      _resultadoValidacao.postValue(retornoValidacao)
      if (retornoValidacao.sucessoValidacaoLogin) {
         _carregando.value = true
         viewModelScope.launch {
            autenticacaoRepositoryImpl.logarUsuario(usuario, uiStatus)
            _carregando.postValue(false)
         }
      }
   }

   fun verificarUsuarioLogado(): Boolean {
      return autenticacaoRepositoryImpl.verificarUsuarioLogado()
   }

   fun deslogarUsuario() {
      firebaseAuth.signOut()
   }

}