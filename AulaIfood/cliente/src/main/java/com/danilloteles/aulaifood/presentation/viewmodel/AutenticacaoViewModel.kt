package com.danilloteles.aulaifood.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.aulaifood.data.remote.firebase.repository.AutenticacaoRepositoryImpl
import com.danilloteles.aulaifood.data.remote.firebase.repository.IAutenticacaoRepository
import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.aulaifood.domain.usecase.AutenticacaoUseCase
import com.danilloteles.aulaifood.domain.usecase.ResultadoValidacao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AutenticacaoViewModel @Inject constructor(
   private val autenticacaoUseCase: AutenticacaoUseCase,
   private val autenticacaoRepositoryImpl: IAutenticacaoRepository
): ViewModel() {

   private val _resultadoValidacao = MutableLiveData<ResultadoValidacao>()
   val resultadoValidacao: LiveData<ResultadoValidacao>
      get() = _resultadoValidacao

   private val _sucesso = MutableLiveData<Boolean>()
   val sucesso: LiveData<Boolean>
      get() = _sucesso

   fun cadastrarUsuario( usuario: Usuario ) {
      val retornoValidacao = autenticacaoUseCase.validarCadastroUsuario( usuario )
      _resultadoValidacao.postValue( retornoValidacao )
      //Cadastro usuário no Firebase
      if ( retornoValidacao.sucessoValidacaoCadastro ){
         viewModelScope.launch {
            val retorno = autenticacaoRepositoryImpl.cadastrarUsuario( usuario )
            _sucesso.postValue( retorno )
         }
      }
   }

   fun logarUsuario( usuario: Usuario ) {
      val retornoValidacao = autenticacaoUseCase.validarLoginUsuario( usuario )
      _resultadoValidacao.postValue( retornoValidacao )
      //Logar usuário no Firebase
   }

}