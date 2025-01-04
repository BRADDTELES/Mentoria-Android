package com.danilloteles.aularoommvvmpratica.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.danilloteles.aularoommvvmpratica.databinding.ItemAnotacaoBinding

class AnotacaoAdapter : Adapter<AnotacaoAdapter.AnotacaoViewHolder>(){

    private val listaAnotacoesCategoria = listOf<String>()

    inner class AnotacaoViewHolder(
        private val binding: ItemAnotacaoBinding
    ) : ViewHolder( binding.root ){
        fun bind(  ) {
            
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnotacaoViewHolder {

        val itemView = ItemAnotacaoBinding.inflate(
            LayoutInflater.from( parent.context ),
            parent,
            false
        )

        return AnotacaoViewHolder( itemView )
    }

    override fun onBindViewHolder(holder: AnotacaoViewHolder, position: Int) {
        val item = listaAnotacoesCategoria[position]
        holder.bind(  )
    }

    override fun getItemCount(): Int {
        return listaAnotacoesCategoria.size
    }
}