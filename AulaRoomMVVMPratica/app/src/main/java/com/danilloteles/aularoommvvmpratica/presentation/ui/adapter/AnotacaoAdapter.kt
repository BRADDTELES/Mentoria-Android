package com.danilloteles.aularoommvvmpratica.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.danilloteles.aularoommvvmpratica.data.entity.relacionamentos.AnotacaoECategoria
import com.danilloteles.aularoommvvmpratica.databinding.ItemAnotacaoBinding

class AnotacaoAdapter : Adapter<AnotacaoAdapter.AnotacaoViewHolder>(){

    private var listaAnotacoesCategoria = listOf<AnotacaoECategoria>()

    fun configurarLista( lista: List<AnotacaoECategoria>){
        listaAnotacoesCategoria = lista
        notifyDataSetChanged()
    }

    inner class AnotacaoViewHolder(
        private val binding: ItemAnotacaoBinding
    ) : ViewHolder( binding.root ){
        fun bind( anotacaoECategoria: AnotacaoECategoria ) {

            val anotacao = anotacaoECategoria.anotacao
            binding.textTituloAnotacao.text = anotacao.titulo
            binding.textDescricaoAnotacao.text = anotacao.descricao
            binding.textCategoriaAnotacao.text = anotacaoECategoria.categoria.nome

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

    override fun getItemCount(): Int {
        return listaAnotacoesCategoria.size
    }

    override fun onBindViewHolder(holder: AnotacaoViewHolder, position: Int) {
        val anotacaoECategoria = listaAnotacoesCategoria[position]
        holder.bind( anotacaoECategoria )
    }

}