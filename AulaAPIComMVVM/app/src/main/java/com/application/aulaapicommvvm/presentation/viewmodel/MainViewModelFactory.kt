package com.application.aulaapicommvvm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.aulaapicommvvm.data.repository.IPostagemRepository
import com.application.aulaapicommvvm.data.repository.PostagemRepository
import com.application.aulaapicommvvm.domain.usecase.PostagemUseCase

class MainViewModelFactory(
    private val postagemUseCase: PostagemUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if ( modelClass.isAssignableFrom( MainViewModel::class.java ) ){
            MainViewModel( postagemUseCase ) as T
        }else{
            throw IllegalArgumentException("ViewModdel não foi configurado corretamente")
        }
    }

}