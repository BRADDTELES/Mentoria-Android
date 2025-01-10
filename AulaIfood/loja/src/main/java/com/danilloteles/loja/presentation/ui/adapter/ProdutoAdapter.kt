package com.danilloteles.loja.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.danilloteles.aulaifood.domain.model.Produto
import com.danilloteles.loja.databinding.ItemRvProdutosBinding
import com.squareup.picasso.Picasso

class ProdutoAdapter(
   private val onClickOpcional: (Produto) -> Unit,
   private val onClickEditar: (Produto) -> Unit,
   private val onClickRemover: (Produto) -> Unit,
) : Adapter<ProdutoAdapter.ProdutosViewHolder>(){

   private var produtos = listOf<Produto>()
   fun adicionarLista( lista: List<Produto> ) {
      produtos = lista
      notifyDataSetChanged()
   }

   inner class ProdutosViewHolder(
      private val binding: ItemRvProdutosBinding
   ) : ViewHolder( binding.root ){
      fun bind( produto: Produto ){
         with( binding ){

            textNomeProdutoLoja.text = produto.nome
            textPrecoProdutoLoja.text = produto.preco

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