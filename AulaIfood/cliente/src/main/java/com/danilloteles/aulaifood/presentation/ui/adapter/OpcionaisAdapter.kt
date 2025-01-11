package com.danilloteles.aulaifood.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.danilloteles.aulaifood.databinding.ItemRvFiltroCategoriaBinding
import com.danilloteles.aulaifood.databinding.ItemRvOpcionaisBinding
import com.danilloteles.aulaifood.domain.model.Opcional
import com.danilloteles.aulaifood.presentation.ui.adapter.FiltroCategoriaAdapter.FiltroCategoriaViewHolder
import com.jamiltondamasceno.core.formatarComoMoeda
import com.squareup.picasso.Picasso

class OpcionaisAdapter
   : Adapter<OpcionaisAdapter.OpcionaisViewHolder>(){

   private var listaOpcionais = listOf<Opcional>()
   fun adicionarItens( listaItens: List<Opcional> ) {
      listaOpcionais = listaItens
      notifyDataSetChanged()
   }

   class OpcionaisViewHolder(
      private val binding: ItemRvOpcionaisBinding
   ) : ViewHolder( binding.root ){
      fun  bind( opcional: Opcional ){

         binding.textNomeOpcionalDetalhe.text = opcional.nome
         binding.textDescricaoOpcionalDetalhe.text = opcional.descricao
         binding.textPrecoOpcionalDetalhe.text = opcional.preco.formatarComoMoeda()

         if ( opcional.url.isNotEmpty() ) {
            Picasso.get()
               .load( opcional.url )
               .into( binding.imageOpcionalDetalhe )
         }
      }
   }

   override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
   ): OpcionaisViewHolder {
      val layoutInflater = LayoutInflater.from( parent.context )
      val binding = ItemRvOpcionaisBinding.inflate(
         layoutInflater, parent, false
      )
      return OpcionaisViewHolder( binding )
   }

   override fun onBindViewHolder(holder: OpcionaisViewHolder, position: Int) {
      val opcional = listaOpcionais[position]
      holder.bind( opcional )
   }

   override fun getItemCount(): Int {
      return listaOpcionais.size
   }

}