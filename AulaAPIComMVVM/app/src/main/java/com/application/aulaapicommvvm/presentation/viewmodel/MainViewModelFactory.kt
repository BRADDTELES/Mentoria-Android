package com.application.aulaapicommvvm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.aulaapicommvvm.data.repository.PostagemRepository

class MainViewModelFactory(
    private val postagemRepository: PostagemRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if ( modelClass.isAssignableFrom( MainViewModel::class.java ) ){
            MainViewModel( postagemRepository ) as T
        }else{
            throw IllegalArgumentException("ViewModdel não foi configurado corretamente")
        }
    }

}