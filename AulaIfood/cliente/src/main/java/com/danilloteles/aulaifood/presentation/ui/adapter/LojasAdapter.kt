package com.danilloteles.aulaifood.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.danilloteles.aulaifood.databinding.ItemRvLojasBinding
import com.danilloteles.aulaifood.databinding.ItemRvUltimasLojasBinding
import com.danilloteles.aulaifood.domain.model.Loja
import com.squareup.picasso.Picasso

class LojasAdapter(
   private val orientacao: Int,
   private val onClick: (Loja) -> Unit
) : Adapter<ViewHolder>(){

   private var listaLojas = listOf<Loja>()
   fun adicionarLista( lista: List<Loja> ) {
      listaLojas = lista
      notifyDataSetChanged()
   }

   inner class LojasViewHolder(
      private val binding: ItemRvLojasBinding
   ) : ViewHolder( binding.root ){
      fun bind( loja: Loja ){
         with( binding ){
            textTituloLoja.text = loja.nome
            textCategoriaLoja.text = loja.categoria
            if ( loja.urlImagem.isNotEmpty() ) {
               Picasso.get()
                  .load( loja.urlImagem )
                  .into( imageLoja )
            }
            clLoja.setOnClickListener{
               onClick(loja)
            }
         }
      }
   }

   inner class UltimasLojasViewHolder(
      private val binding: ItemRvUltimasLojasBinding
   ) : ViewHolder( binding.root ){
      fun bind( loja: Loja ){
         with( binding ){
            textTituloUltimaLoja.text = loja.nome
            if ( loja.urlImagem.isNotEmpty() ) {
               Picasso.get()
                  .load( loja.urlImagem )
                  .into( imageUltimaLoja )
            }
            clUltimaLoja.setOnClickListener{
               onClick(loja)
            }
         }
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

      val layoutInflater = LayoutInflater.from( parent.context )
      if ( orientacao == RecyclerView.VERTICAL ) {
         val itemRvLojas = ItemRvLojasBinding.inflate(
            layoutInflater, parent, false
         )
         return LojasViewHolder( itemRvLojas )
      }
      val itemRvUltimasLojas = ItemRvUltimasLojasBinding.inflate(
         layoutInflater, parent, false
      )
      return UltimasLojasViewHolder( itemRvUltimasLojas )
   }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val loja = listaLojas[position]
      when( holder ){
         is LojasViewHolder -> holder.bind( loja )
         is UltimasLojasViewHolder -> holder.bind( loja )
      }
   }

   override fun getItemCount(): Int {
      return listaLojas.size
   }

}