package com.danilloteles.aularoommvvmpratica.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria
import com.danilloteles.aularoommvvmpratica.data.repository.CategoriaRepository
import com.danilloteles.aularoommvvmpratica.data.repository.ResultadoOperacao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriaViewModel @Inject constructor(
    private val categoriaRepository: CategoriaRepository
) : ViewModel() {

    private val _resultadoOperacao = MutableLiveData<ResultadoOperacao>()
    val resultadoOperacao: LiveData<ResultadoOperacao>
        get() = _resultadoOperacao

    fun salvar( categoria: Categoria ) {
        viewModelScope.launch( Dispatchers.IO ){
            val resultadoOperacao = categoriaRepository.salvar( categoria )
            _resultadoOperacao.postValue( resultadoOperacao )
        }
    }
}