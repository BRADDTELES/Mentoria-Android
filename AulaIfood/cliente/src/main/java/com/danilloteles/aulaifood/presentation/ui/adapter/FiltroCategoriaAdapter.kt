package com.danilloteles.aulaifood.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.danilloteles.aulaifood.databinding.ItemRvFiltroCategoriaBinding
import com.danilloteles.aulaifood.domain.model.FiltroCategoria
import com.squareup.picasso.Picasso

class FiltroCategoriaAdapter : Adapter<FiltroCategoriaAdapter.FiltroCategoriaViewHolder>() {

   class FiltroCategoriaViewHolder(
      private val binding: ItemRvFiltroCategoriaBinding
   ) : ViewHolder( binding.root ){
      fun  bind( filtroCategoria: FiltroCategoria ){
         binding.textTituloFiltroCategoria.text = filtroCategoria.nome
         //binding.imageFiltroCategoria.setImageResource( filtroCategoria.idDrawable)
         if ( filtroCategoria.url.isNotEmpty() ) {
         //if ( filtroCategoria.idDrawable.isNotEmpty() ) {
            Picasso.get()
               .load( filtroCategoria.url )
               //.load( filtroCategoria.idDrawable )
               .into( binding.imageFiltroCategoria )
         }
      }
   }

   private var listaFiltrosCategoria = listOf<FiltroCategoria>()
   fun adicionarItens( listaItens: List<FiltroCategoria> ) {
      listaFiltrosCategoria = listaItens
      notifyDataSetChanged()
   }

   override fun onCreateViewHolder(
      parent: ViewGroup, viewType: Int
   ): FiltroCategoriaViewHolder {
      val layoutInflater = LayoutInflater.from( parent.context )
      val binding = ItemRvFiltroCategoriaBinding.inflate(
         layoutInflater, parent, false
      )
      return FiltroCategoriaViewHolder( binding )
   }

   override fun getItemCount(): Int {
      return listaFiltrosCategoria.size
   }

   override fun onBindViewHolder(holder: FiltroCategoriaViewHolder, position: Int) {
      val filtroCategoria = listaFiltrosCategoria[position]
      holder.bind( filtroCategoria )
   }

}