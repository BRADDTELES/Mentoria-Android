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
import com.danilloteles.aulaifood.databinding.FragmentProdutoBinding
import com.danilloteles.aulaifood.domain.model.Loja
import com.danilloteles.aulaifood.domain.model.Produto
import com.danilloteles.aulaifood.presentation.ui.adapter.OpcionaisAdapter
import com.danilloteles.aulaifood.presentation.viewmodel.ProdutoViewModel
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.exibirMensagem
import com.jamiltondamasceno.core.formatarComoMoeda
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProdutoFragment : Fragment() {

   private lateinit var binding: FragmentProdutoBinding
   private lateinit var opcionaisAdapter: OpcionaisAdapter
   private val produtoFragmentArgs: ProdutoFragmentArgs by navArgs()
   private lateinit var loja: Loja
   private lateinit var produto: Produto
   private val produtoViewModel: ProdutoViewModel by viewModels()
   private val alertaCarregamento by lazy {
      AlertaCarregamento( requireContext() )
   }

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {

      binding = FragmentProdutoBinding.inflate(
         inflater,
         container,
         false
      )

      inicializarOpcionais()
      inicializarEventosClique()

      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      exibirDadosProduto()
      listarOpcionais()
   }

   private fun listarOpcionais() {
      produtoViewModel.listarOpcionais(
         loja.idLoja, produto.id
      ){ uiStatus ->
         when ( uiStatus ) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               activity?.exibirMensagem( uiStatus.erro )
            }
            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               val opcionais = uiStatus.dados
               opcionaisAdapter.adicionarItens( opcionais )
            }
            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Carregando dados de Opcionais")
            }
         }
      }
   }

   private fun exibirDadosProduto() {
      loja = produtoFragmentArgs.loja
      produto = produtoFragmentArgs.produto

      if ( produto.url.isNotEmpty() ) {
         Picasso.get()
            .load( produto.url )
            .into(binding.imageCapaProduto)
      }
      if ( produto.nome.isNotEmpty() ) {
         binding.textNomeProdutoDetalhe.text = produto.nome
      }
      if ( produto.descricao.isNotEmpty() ) {
         binding.textDescricaoProdutoDetalhe.text = produto.descricao
      }
      if (produto.emDestaque == true ) {
         binding.textPrecoProdutoDetalhe.text = produto.precoDestaque.formatarComoMoeda()
      }else{
         binding.textPrecoProdutoDetalhe.text = produto.preco.formatarComoMoeda()
      }

   }

   private fun inicializarOpcionais() {
      with( binding ){
         opcionaisAdapter = OpcionaisAdapter()

         rvOpcionaisProdutoDetlahe.adapter = opcionaisAdapter
         rvOpcionaisProdutoDetlahe.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL, false
         )
      }
   }

   private fun inicializarEventosClique() {
      val navController = findNavController()
      binding.btnProdutoVoltar.setOnClickListener {
         navController.popBackStack()
      }
      binding.btnAdicionarProdutoCarrinho.setOnClickListener {
         navController.popBackStack()
         //navController.navigate(R.id.lojaFragment)
      }
   }

}