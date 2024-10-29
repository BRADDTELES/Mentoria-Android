package com.application.desafiotestedeempregosimulacao

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.application.desafiotestedeempregosimulacao.databinding.ItemGaleriaBinding
import com.squareup.picasso.Picasso

class GaleriaAdapter : Adapter<GaleriaAdapter.GaleriaViewHolder>(){

    private var listaImagens = emptyList<String>()

    fun adicionarLista( lista: List<String> ){
        listaImagens = lista
        notifyDataSetChanged()
    }

    // ItemGaleriaBinding é o item_galeria.xml
    inner class GaleriaViewHolder(val binding: ItemGaleriaBinding)
        : ViewHolder( binding.root ){

            // Receber a Url da imagem e fazer a exibição utilizando a biblioteca Picasso
            fun bind( url: String ){

                Picasso.get()
                    .load( url )
                    .into( binding.imageItem )// faz carregar

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleriaViewHolder {

        val layoutInflater = LayoutInflater.from( parent.context )
        val itemView = ItemGaleriaBinding.inflate(
            layoutInflater, parent, false
        )
        return GaleriaViewHolder( itemView )
    }

    override fun onBindViewHolder(holder: GaleriaViewHolder, position: Int) {
        val url = listaImagens[position] // pegar a URL da Imagem
        holder.bind( url )
    }

    override fun getItemCount(): Int {
        return listaImagens.size // Retorna a tamanho de itens da lista
    }

}