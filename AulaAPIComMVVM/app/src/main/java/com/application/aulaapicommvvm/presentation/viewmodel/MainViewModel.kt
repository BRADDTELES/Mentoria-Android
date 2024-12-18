package com.application.aulaapicommvvm.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.aulaapicommvvm.data.model.PostagemResposta
import com.application.aulaapicommvvm.data.repository.IPostagemRepository
import com.application.aulaapicommvvm.domain.model.Postagem
import com.application.aulaapicommvvm.domain.usecase.PostagemUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val postagemUseCase: PostagemUseCase
) : ViewModel() {

    var listaPostagens = MutableLiveData<List<Postagem>>()
        //get() = repository.listaPostagensRepository

    fun recuperarPostagens(){
        viewModelScope.launch {
            val postagens = postagemUseCase()
            listaPostagens.postValue( postagens )
        }
    }

}