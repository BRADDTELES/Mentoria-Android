package com.application.aulaapicommvvm.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.aulaapicommvvm.data.model.PostagemResposta
import com.application.aulaapicommvvm.data.repository.IPostagemRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: IPostagemRepository
) : ViewModel() {

    var listaPostagens = MutableLiveData<List<PostagemResposta>>()
        //get() = repository.listaPostagensRepository

    fun recuperarPostagens(){
        viewModelScope.launch {
            repository.recuperarPostagens()

        }
    }

}