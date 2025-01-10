package com.danilloteles.aulaifood.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.danilloteles.aulaifood.databinding.ItemRvPedidosHistoricoBinding
import com.danilloteles.aulaifood.domain.model.PedidoHistorico
import com.squareup.picasso.Picasso

class PedidosHistoricoAdapter(
   val onClick: (PedidoHistorico) -> Unit
) : Adapter<PedidosHistoricoAdapter.PedidosHistoricoViewHolder>() {

   private var listaPedidos = listOf<PedidoHistorico>()
   fun adicionarItens( listaItens: List<PedidoHistorico> ) {
      listaPedidos = listaItens
      notifyDataSetChanged()
   }

   inner class PedidosHistoricoViewHolder(
      private val binding: ItemRvPedidosHistoricoBinding
   ) : ViewHolder( binding.root ){
      fun  bind( pedidoHistorico: PedidoHistorico ){

         binding.textDataPedidoHistorico.text = pedidoHistorico.data
         binding.textNomeLojaPedidoHistorico.text = pedidoHistorico.nomeLoja
         if ( pedidoHistorico.urlLoja.isNotEmpty() ) {
            Picasso.get()
               .load( pedidoHistorico.urlLoja )
               .into( binding.imageLojaPedidoHistorico )
         }
         var produtosPedido = ""
         pedidoHistorico.itens.forEachIndexed{ indice, nomeProduto ->
            produtosPedido += "$indice - $nomeProduto\n"
         }
         binding.textProdutosPedidoHistorico.text = produtosPedido
         binding.clProdutoPedidosHistorico.setOnClickListener{
            onClick( pedidoHistorico )
         }

      }
   }

   override fun onCreateViewHolder(
      parent: ViewGroup,
      viewType: Int
   ): PedidosHistoricoViewHolder {
      val layoutInflater = LayoutInflater.from( parent.context )
      val binding = ItemRvPedidosHistoricoBinding.inflate(
         layoutInflater, parent, false
      )
      return PedidosHistoricoViewHolder( binding )
   }

   override fun onBindViewHolder(holder: PedidosHistoricoViewHolder, position: Int) {
      val pedido = listaPedidos[position]
      holder.bind( pedido )
   }

   override fun getItemCount(): Int {
      return listaPedidos.size
   }

}