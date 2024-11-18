package com.application.aulaapicommvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.aulaapicommvvm.model.Postagem
import com.application.aulaapicommvvm.model.PostagemAPI
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val listaPostagens = MutableLiveData< List<Postagem> >()


    fun recuperarPostagens(){

        val postagemAPI = PostagemAPI()
        viewModelScope.launch {
            val postagens = postagemAPI.recuperarPostagens()
            listaPostagens.postValue( postagens )
        }
    }

}