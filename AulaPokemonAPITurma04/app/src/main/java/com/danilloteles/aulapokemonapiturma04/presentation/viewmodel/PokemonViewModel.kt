package com.danilloteles.aulapokemonapiturma04.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.aulapokemonapiturma04.data.remote.api.repository.IPokemonRepository
import com.danilloteles.aulapokemonapiturma04.data.remote.api.repository.PokemonRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    val pokemonRepositoryImpl: IPokemonRepository
): ViewModel(){

    fun recuperarPokemons() {
        viewModelScope.launch {
            val lista = pokemonRepositoryImpl.recuperarPokemons()
        }
    }

}