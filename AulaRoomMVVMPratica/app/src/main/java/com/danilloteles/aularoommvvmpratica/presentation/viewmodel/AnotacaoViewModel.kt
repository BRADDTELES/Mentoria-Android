package com.danilloteles.aularoommvvmpratica.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.aularoommvvmpratica.data.entity.Anotacao
import com.danilloteles.aularoommvvmpratica.data.entity.relacionamentos.AnotacaoECategoria
import com.danilloteles.aularoommvvmpratica.data.repository.AnotacaoRepository
import com.danilloteles.aularoommvvmpratica.data.repository.ResultadoOperacao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnotacaoViewModel @Inject constructor(
    private val anotacaoRepository: AnotacaoRepository
): ViewModel() {

    private val _resultadoOperacao = MutableLiveData<ResultadoOperacao>()
    val resultadoOperacao: LiveData<ResultadoOperacao>
        get() = _resultadoOperacao

    private val _listaAnotacaoECategorias = MutableLiveData<List<AnotacaoECategoria>>()
    val listaAnotacaoECategorias: LiveData<List<AnotacaoECategoria>>
        get() = _listaAnotacaoECategorias

    fun salvar( anotacao: Anotacao ) {
        if ( validarDadosAnotacao( anotacao ) ) {
            viewModelScope.launch( Dispatchers.IO ){
                val resultadoOperacao = anotacaoRepository.salvar( anotacao )
                _resultadoOperacao.postValue( resultadoOperacao )
            }
        }
    }

    fun listarAnotacaoECategoria() {
        viewModelScope.launch( Dispatchers.IO ){
            val lista = anotacaoRepository.listarAnotacaoECategoria()
            _listaAnotacaoECategorias.postValue( lista )
        }
    }

    fun pesquisarAnotacaoECategoria( texto: String ) {
        viewModelScope.launch( Dispatchers.IO ){
            val lista = anotacaoRepository.pesquisarAnotacaoECategoria(texto)
            _listaAnotacaoECategorias.postValue( lista )
        }
    }

    private fun validarDadosAnotacao(anotacao: Anotacao ) : Boolean {

        if ( anotacao.titulo.isEmpty() ) {
            _resultadoOperacao.value = ResultadoOperacao(
                false, "Preencha o titulo da anotação!"
            )
            return false
        }

        if ( anotacao.idCategoria <= 0 ) {
            _resultadoOperacao.value = ResultadoOperacao(
                false, "Preencha a categoria da anotação!"
            )
            return false
        }

        if ( anotacao.descricao.isEmpty() ) {
            _resultadoOperacao.value = ResultadoOperacao(
                false, "Preencha o descrição da anotação!"
            )
            return false
        }

        return true
    }
}