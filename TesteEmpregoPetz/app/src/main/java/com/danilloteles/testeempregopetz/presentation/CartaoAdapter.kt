package com.danilloteles.testeempregopetz.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.danilloteles.testeempregopetz.data.model.Carta
import com.danilloteles.testeempregopetz.databinding.ItemRvCartaBinding
import com.squareup.picasso.Picasso

class CartaoAdapter : Adapter<CartaoAdapter.CartaoViewHolder>(){

    inner class CartaoViewHolder(
        private val binding: ItemRvCartaBinding
    ) : ViewHolder(binding.root) {

        fun bind( carta: Carta ) {

            with( binding ){

                textNome.text = carta.name

                Log.i("mensagem_api_cartas", "url: ${carta.img} \n" )
                /*Picasso.get()
                    .load( carta.img )
                    .into( imageCarta )*/
                Glide.with( binding.root.context )
                    .load( carta.img )
                    .into( imageCarta )

            }

            /* Pode também fazer dessa forma
            binding.textNome.text = carta.name

            if ( carta.img.isNotEmpty() ) {
                Picasso.get()
                    .load( carta.img )
                    .into( binding.imageCarta )

            }*/

        }

    }

    private var listaCartas = emptyList<Carta>()
    fun atualizarLista( lista: List<Carta> ) {
        listaCartas = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartaoViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = ItemRvCartaBinding.inflate(
            layoutInflater, parent, false
        )

        return CartaoViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return listaCartas.size
    }

    override fun onBindViewHolder(holder: CartaoViewHolder, position: Int) {
        val carta = listaCartas[position]
        holder.bind( carta )
    }
}