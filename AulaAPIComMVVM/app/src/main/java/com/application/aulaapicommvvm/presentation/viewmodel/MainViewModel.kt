package com.application.aulaapicommvvm.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.aulaapicommvvm.data.model.Postagem
import com.application.aulaapicommvvm.data.repository.PostagemRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val postagemRepository: PostagemRepository
) : ViewModel() {

    var listaPostagens = MutableLiveData<List<Postagem>>()
        get() = postagemRepository.listaPostagensRepository

    fun recuperarPostagens(){
        viewModelScope.launch {
            postagemRepository.recuperarPostagens()

        }
    }

}