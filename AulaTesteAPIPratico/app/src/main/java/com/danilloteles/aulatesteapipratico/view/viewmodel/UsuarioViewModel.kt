package com.danilloteles.aulatesteapipratico.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.aulatesteapipratico.data.remote.dto.Usuario
import com.danilloteles.aulatesteapipratico.domain.UsuarioUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsuarioViewModel(
    private val usuarioUseCase: UsuarioUseCase
) : ViewModel() {

    private val _listaUsuarios = MutableLiveData<List<Usuario>>()
    val listaUsuarios: LiveData<List<Usuario>>
        get() = _listaUsuarios


    fun recuperarListaUsuarios(){
        viewModelScope.launch( Dispatchers.IO ) {
            val listaUsuarios = usuarioUseCase()//Fake ou Mock
            _listaUsuarios.postValue( listaUsuarios )
        }
    }

}