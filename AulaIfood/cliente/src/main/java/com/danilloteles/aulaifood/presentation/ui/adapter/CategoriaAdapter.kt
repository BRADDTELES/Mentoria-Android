package com.danilloteles.aulaifood.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.danilloteles.aulaifood.data.remote.dto.CategoriaDTO
import com.danilloteles.aulaifood.databinding.ItemFiltroCategoriasBinding
import com.squareup.picasso.Picasso

class CategoriaAdapter : Adapter<CategoriaAdapter.CategoriaViewHolder>() {

   private var listaCategorias = listOf<CategoriaDTO>()
   fun adicionarLista( lista: List<CategoriaDTO>) {
      listaCategorias = lista
      notifyDataSetChanged()
   }

   inner class CategoriaViewHolder(
      private val binding: ItemFiltroCategoriasBinding
   ) : ViewHolder( binding.root ){
      fun bind( categoriaDTO: CategoriaDTO ){
         binding.textNomeCategoria.text = categoriaDTO.nomeCategoria.toString()
         if ( categoriaDTO != null ){
            Picasso.get()
               .load(categoriaDTO.imagemCategoria)
               .into(binding.imageCategoria)
         }
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
      val layoutInflater = LayoutInflater.from( parent.context )
      val itemFiltroCategoriasBinding = ItemFiltroCategoriasBinding.inflate(
         layoutInflater,
         parent,
         false
      )
      return CategoriaViewHolder( itemFiltroCategoriasBinding )
   }

   override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
      val categoria = listaCategorias[position]
      holder.bind( categoria )
   }

   override fun getItemCount(): Int {
      return listaCategorias.size
   }
}