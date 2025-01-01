package com.danilloteles.aulapokemonapiturma04.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.danilloteles.aulapokemonapiturma04.data.remote.dto.PokemonDTO
import com.danilloteles.aulapokemonapiturma04.databinding.ItemRvPokemonBinding
import com.squareup.picasso.Picasso

class PokemonAdapter : Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var listaPokemons = listOf<PokemonDTO>()
    fun adicionarLista( lista: List<PokemonDTO> ){
        listaPokemons = lista
        notifyDataSetChanged()
    }

    inner class PokemonViewHolder(
        private val binding: ItemRvPokemonBinding
    ) : ViewHolder( binding.root ){
        fun bind( pokemonDTO: PokemonDTO ){
            binding.textNomePokemon.text = pokemonDTO.name
            if (pokemonDTO.url.isNotEmpty()) {
                Picasso.get()
                    .load( pokemonDTO.url )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from( parent.context )
        val itemRvPokemonBinding = ItemRvPokemonBinding.inflate(
            layoutInflater, parent, false
        )
        return PokemonViewHolder( itemRvPokemonBinding )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = listaPokemons[position]
        holder.bind( pokemon )
    }

    override fun getItemCount(): Int {
        return listaPokemons.size
    }

}