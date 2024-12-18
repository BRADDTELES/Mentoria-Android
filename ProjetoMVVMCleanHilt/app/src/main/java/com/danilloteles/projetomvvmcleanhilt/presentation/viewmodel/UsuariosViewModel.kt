package com.danilloteles.projetomvvmcleanhilt.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.projetomvvmcleanhilt.domain.model.Usuario
import com.danilloteles.projetomvvmcleanhilt.domain.usecase.GetUsuariosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuariosViewModel @Inject constructor(
    private val usuariosUseCase: GetUsuariosUseCase
) : ViewModel() {

    //Privado
    private val _usuarios = MutableLiveData<List<Usuario>>()

    //LiveData Público
    val usuarios: LiveData<List<Usuario>>
        get() = _usuarios

    init {
        recuperarUsuarios()
    }

    fun recuperarUsuarios(){
        viewModelScope.launch{
            val listaUsuarios = usuariosUseCase.invoke()
            _usuarios.postValue( listaUsuarios )
        }
    }

}