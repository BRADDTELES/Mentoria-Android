package com.application.projetoturmafirebase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.application.projetoturmafirebase.databinding.ItemViagemBinding

class ViagemAdapter : Adapter<ViagemAdapter.ViagemViewHolder>(){

    private var listaViagens = emptyList<String>()

    fun adicionarLista( lista: List<String>){
        listaViagens = lista
        notifyDataSetChanged()
    }

    inner class ViagemViewHolder(val binding: ItemViagemBinding)
        : ViewHolder( binding.root ){

            fun bind(viagem: String){
                binding.textViagem.text = viagem
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViagemViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = ItemViagemBinding.inflate(
            layoutInflater, parent, false
        )
        return ViagemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViagemViewHolder, position: Int) {
        val viagem = listaViagens[position]
        holder.bind(viagem)
    }

    override fun getItemCount(): Int {
        return listaViagens.size
    }
}