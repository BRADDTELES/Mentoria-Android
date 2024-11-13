package com.application.aulawhatsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.application.aulawhatsapp.databinding.ItemContatosBinding
import com.application.aulawhatsapp.model.Usuario
import com.squareup.picasso.Picasso

class ContatosAdapter(
    private val onClick: (Usuario) -> Unit
) : Adapter<ContatosAdapter.ContatosViewHoldder>() {

    private var listaContatos = emptyList<Usuario>()
    fun adicionarLista( lista: List<Usuario> ){
        listaContatos = lista
        notifyDataSetChanged()
    }
    inner class ContatosViewHoldder(private val binding: ItemContatosBinding) : ViewHolder(binding.root){

        fun bind( usuario: Usuario ){

            binding.textContatoNome.text = usuario.nome
            Picasso.get()
                .load( usuario.foto )
                .into( binding.imageContatoFoto )

            //Evento de clique
            binding.clItemContato.setOnClickListener {
                onClick( usuario )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatosViewHoldder {

        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemContatosBinding.inflate(
            inflater, parent, false
        )
        return ContatosViewHoldder( itemView )
    }

    override fun onBindViewHolder(holder: ContatosViewHoldder, position: Int) {
        val usuario = listaContatos[position]
        holder.bind( usuario )
    }

    override fun getItemCount(): Int {
        return listaContatos.size
    }

}