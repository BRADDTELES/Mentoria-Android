package com.danilloteles.loja.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.danilloteles.loja.databinding.ItemRvOpcionalBinding
import com.danilloteles.loja.domain.model.Opcional
import com.squareup.picasso.Picasso

class OpcionaisAdapter(
   private val onClickRemover: (Opcional) -> Unit
) : Adapter<OpcionaisAdapter.OpcionaisViewHolder>(){

   private var listaOpcionais = listOf<Opcional>()
   fun adicionarItens( listaItens: List<Opcional> ) {
      listaOpcionais = listaItens
      notifyDataSetChanged()
   }

   inner class OpcionaisViewHolder(
      private val binding: ItemRvOpcionalBinding
   ) : ViewHolder( binding.root ){
      fun  bind( opcional: Opcional ){

         binding.textNomeOpcional.text = opcional.nome
         binding.textDescricaoOpcional.text = opcional.descricao
         binding.textPrecoOpcional.text = opcional.preco

         if ( opcional.url.isNotEmpty() ) {
            Picasso.get()
               .load( opcional.url )
               .into( binding.imageOpcional )
         }

         binding.btnRemoverOpcional.setOnClickListener {
            onClickRemover( opcional )
         }
      }
   }

   override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
   ): OpcionaisViewHolder {
      val layoutInflater = LayoutInflater.from( parent.context )
      val binding = ItemRvOpcionalBinding.inflate(
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