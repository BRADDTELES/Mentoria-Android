package com.danilloteles.loja.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.danilloteles.loja.databinding.ItemRvProdutosBinding
import com.danilloteles.loja.domain.model.Produto
import com.jamiltondamasceno.core.formatarComoMoeda
import com.squareup.picasso.Picasso
import java.util.Locale

class ProdutosAdapter(
   private val onClickOpcional: (Produto) -> Unit,
   private val onClickEditar: (Produto) -> Unit,
   private val onClickRemover: (Produto) -> Unit,
) : Adapter<ProdutosAdapter.ProdutosViewHolder>(){

   private var produtos = listOf<Produto>()
   fun adicionarLista( lista: List<Produto> ) {
      produtos = lista
      notifyDataSetChanged()
   }

   inner class ProdutosViewHolder(
      private val binding: ItemRvProdutosBinding
   ) : ViewHolder( binding.root ){
      fun bind( produto: Produto){
         with( binding ){

            textNomeProdutoLoja.text = produto.nome

            val precoFormatado = produto.preco.formatarComoMoeda(
               local = Locale("pt", "BR"),
               maximoDigitosDecimais = 2,
               simboloMoedaCustomizado = "R$"
            )

            val precoDestaqueFormatado = produto.precoDestaque.formatarComoMoeda(
               local = Locale("pt", "BR"),
               maximoDigitosDecimais = 2,
               simboloMoedaCustomizado = "R$"
            )

            val textoPrecos = if ( produto.emDestaque == true ){
               "[$precoDestaqueFormatado] - $precoFormatado"
            }else{
               precoFormatado
            }

            textPrecoProdutoLoja.text = textoPrecos

            if ( produto.emDestaque == true ) {
               textDestaqueProdutoLoja.visibility = View.VISIBLE
            }else{
               textDestaqueProdutoLoja.visibility = View.GONE
            }

            if ( produto.url.isNotEmpty() ) {
               Picasso.get()
                  .load( produto.url )
                  .into( imageProdutoLoja )
            }

            btnAdicionarOpcional.setOnClickListener {
               onClickOpcional(produto)
            }

            btnEditarProduto.setOnClickListener {
               onClickEditar(produto)
            }

            btnRemoverProduto.setOnClickListener {
               onClickRemover(produto)
            }

         }
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutosViewHolder {
      val layoutInflater = LayoutInflater.from( parent.context )
      val binding = ItemRvProdutosBinding.inflate(
         layoutInflater, parent, false
      )
      return ProdutosViewHolder( binding )
   }

   override fun onBindViewHolder(holder: ProdutosViewHolder, position: Int) {
      val produto = produtos[position]
      holder.bind( produto )
   }

   override fun getItemCount(): Int {
      return produtos.size
   }

}