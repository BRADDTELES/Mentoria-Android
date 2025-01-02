package com.danilloteles.aulapokemonapiturma04.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonDTO
import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonDetalheDTO
import com.danilloteles.aulapokemonapiturma04.data.remote.api.repository.IPokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    val pokemonRepositoryImpl: IPokemonRepository
): ViewModel(){

    private val _listaPokemons = MutableLiveData<List<PokemonDTO>>()
    val listaPokemons: LiveData<List<PokemonDTO>>
        get() = _listaPokemons

    private val _pokemonDetalhe = MutableLiveData<PokemonDetalheDTO?>()
    val pokemonDetalhe: LiveData<PokemonDetalheDTO?>
        get() = _pokemonDetalhe

    fun recuperarPokemons() {
        viewModelScope.launch {
            val lista = pokemonRepositoryImpl.recuperarPokemons()
            /*lista.forEach { pokemon ->
                //PokemonDetalhe
                val pokemonDetalhe = pokemonRepositoryImpl.recuperarPokemon( pokemon.name )
            }*/
            _listaPokemons.postValue( lista )
        }
    }

    fun recuperarPokemonDetalhe( nomePokemon: String ) {
        viewModelScope.launch {
            val pokemonDetalhe = pokemonRepositoryImpl.recuperarPokemonDetalhe( nomePokemon )
            _pokemonDetalhe.postValue( pokemonDetalhe )
        }
    }

}