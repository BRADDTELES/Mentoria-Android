package com.danilloteles.testeempregopetz.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.testeempregopetz.data.model.Carta
import com.danilloteles.testeempregopetz.data.repository.CartaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class CartoesViewModel(
    private val cartaRepository: CartaRepository
) : ViewModel() {

    private val _listaCartas = MutableLiveData<List<Carta>>()
    val listaCartas: LiveData<List<Carta>>
        get() = _listaCartas

    fun recuperarCartoes(){

        viewModelScope.launch {
            val lista = cartaRepository.recuperarCartas()
            _listaCartas.postValue( lista )
        }

    }

}