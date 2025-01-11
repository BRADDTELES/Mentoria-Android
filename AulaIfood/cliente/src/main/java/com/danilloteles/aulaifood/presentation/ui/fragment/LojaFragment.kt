package com.danilloteles.aulaifood.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danilloteles.aulaifood.R
import com.danilloteles.aulaifood.databinding.FragmentHomeBinding
import com.danilloteles.aulaifood.databinding.FragmentLojaBinding
import com.danilloteles.aulaifood.domain.model.Loja
import com.danilloteles.aulaifood.domain.model.Produto
import com.danilloteles.aulaifood.domain.model.TipoProduto
import com.danilloteles.aulaifood.presentation.ui.adapter.LojasAdapter
import com.danilloteles.aulaifood.presentation.ui.adapter.ProdutoAdapter
import com.danilloteles.aulaifood.presentation.viewmodel.LojaViewModel
import com.danilloteles.aulaifood.presentation.viewmodel.ProdutoViewModel
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.exibirMensagem
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LojaFragment : Fragment() {

   private lateinit var binding: FragmentLojaBinding

   private lateinit var produtoAdapter: ProdutoAdapter
   private lateinit var produtoAdapterDestaque: ProdutoAdapter
   private val lojaFragmentArgs: LojaFragmentArgs by navArgs()
   private val alertaCarregamento by lazy {
      AlertaCarregamento( requireContext() )
   }
   private val produtoViewModel: ProdutoViewModel by viewModels()
   private lateinit var loja: Loja

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {

      binding = FragmentLojaBinding.inflate(
         inflater,
         container,
         false
      )

      inicializarEventosClique()
      inicializarProdutosDestaque()
      inicializarProdutos()

      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      exibirDadosLoja()
      listarProdutos()
   }

   private fun listarProdutos() {
      produtoViewModel.listar( loja.idLoja ){ uiStatus ->
         when (uiStatus) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               activity?.exibirMensagem(uiStatus.erro)
            }
            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               val produtosSeparados = uiStatus.dados

               val produtosEmDestaque = produtosSeparados
                  .find {
                     it.tipo == TipoProduto.PRODUTOS_EM_DESTAQUE
                  }?.lista ?: emptyList()

               val produtos = produtosSeparados
                  .find {
                     it.tipo == TipoProduto.PRODUTOS
                  }?.lista ?: emptyList()

               produtoAdapter.adicionarLista( produtos )
               produtoAdapterDestaque.adicionarLista( produtosEmDestaque )
            }
            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Carregando produtos")
            }
         }
      }
   }

   private fun exibirDadosLoja() {

      loja = lojaFragmentArgs.loja
      if ( loja.urlCapa.isNotEmpty() ) {
         Picasso.get()
            .load(loja.urlCapa)
            .into(binding.imageCapaLoja)
      }
      if ( loja.urlPerfil.isNotEmpty() ) {
         Picasso.get()
            .load(loja.urlPerfil)
            .into(binding.imagePerfilLoja)
      }
      if ( loja.nome.isNotEmpty() ) {
         binding.textNomeLoja.text = loja.nome
      }
      if ( loja.categoria.isNotEmpty() ) {
         binding.textCategoriaLoja.text = loja.categoria
      }
   }

   private fun inicializarProdutosDestaque() {
      with( binding ){
         val orientacao = RecyclerView.HORIZONTAL
         produtoAdapterDestaque = ProdutoAdapter(orientacao){ produto ->
            val acao = LojaFragmentDirections
               .actionLojaFragmentToProdutoFragment(
                  produto = produto,
                  loja = loja
               )
            findNavController().navigate( acao )
         }
         rvDestaqueProdutosLoja.adapter = produtoAdapterDestaque
         rvDestaqueProdutosLoja.layoutManager = LinearLayoutManager(
            context, orientacao, false
         )
      }
   }

   private fun inicializarProdutos() {
      with( binding ){
         val orientacao = RecyclerView.VERTICAL
         produtoAdapter = ProdutoAdapter(orientacao){ produto ->
            val acao = LojaFragmentDirections
               .actionLojaFragmentToProdutoFragment(
                  produto = produto,
                  loja = loja
               )
            findNavController().navigate( acao )
         }
         rvProdutosLoja.adapter = produtoAdapter
         rvProdutosLoja.layoutManager = LinearLayoutManager(
            context, orientacao, false
         )
      }
   }

   private fun inicializarEventosClique() {
      val navController = findNavController()

      binding.btnLojaVoltar.setOnClickListener {
         navController.navigate(R.id.homeFragment)
      }

      binding.btnVerItensFinalizarPedido.setOnClickListener {
         navController.navigate(R.id.action_lojaFragment_to_finalizarPedidoFragment)
      }
   }

}