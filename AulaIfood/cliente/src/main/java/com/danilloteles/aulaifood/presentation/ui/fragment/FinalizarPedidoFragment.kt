package com.danilloteles.aulaifood.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danilloteles.aulaifood.R
import com.danilloteles.aulaifood.databinding.FragmentFinalizarPedidoBinding
import com.danilloteles.aulaifood.databinding.FragmentLojaBinding
import com.danilloteles.aulaifood.domain.model.Produto
import com.danilloteles.aulaifood.presentation.ui.adapter.ProdutoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinalizarPedidoFragment : Fragment() {

   private lateinit var binding: FragmentFinalizarPedidoBinding
   private lateinit var produtoAdapter: ProdutoAdapter


   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      binding = FragmentFinalizarPedidoBinding.inflate(
         inflater,
         container,
         false
      )

      //inicializarProdutosDestaque()
      inicializarEventosClique()
      inicializarProdutos()

      return binding.root
   }

   private fun inicializarEventosClique() {
      binding.btnFinalizarPedidoVoltar.setOnClickListener {
         findNavController().popBackStack()
      }
   }

   private fun inicializarProdutos() {
      with( binding ){
         val orientacao = RecyclerView.VERTICAL
         produtoAdapter = ProdutoAdapter( orientacao ){}
         rvProdutosFinalizarPedido.adapter = produtoAdapter
         rvProdutosFinalizarPedido.layoutManager = LinearLayoutManager(
            context, orientacao, false
         )
      }
   }

}