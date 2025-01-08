package com.danilloteles.aulaifood.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.aulaifood.domain.usecase.AutenticacaoUseCase
import com.danilloteles.aulaifood.domain.usecase.ResultadoValidacao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AutenticacaoViewModel @Inject constructor(
   private val autenticacaoUseCase: AutenticacaoUseCase
): ViewModel() {

   private val _resultadoValidacao = MutableLiveData<ResultadoValidacao>()
   val resultadoValidacao: LiveData<ResultadoValidacao>
      get() = _resultadoValidacao

   fun cadastrarUsuario( usuario: Usuario ) {
      val retornoValidacao = autenticacaoUseCase.validarCadastroUsuario( usuario )
      _resultadoValidacao.postValue( retornoValidacao )
      //Cadastro usuário no Firebase
   }

   fun logarUsuario( usuario: Usuario ) {
      val retornoValidacao = autenticacaoUseCase.validarLoginUsuario( usuario )
      _resultadoValidacao.postValue( retornoValidacao )
      //Logar usuário no Firebase
   }

}