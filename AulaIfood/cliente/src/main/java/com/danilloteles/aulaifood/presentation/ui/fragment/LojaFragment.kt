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
import com.danilloteles.aulaifood.databinding.FragmentHomeBinding
import com.danilloteles.aulaifood.databinding.FragmentLojaBinding
import com.danilloteles.aulaifood.domain.model.Produto
import com.danilloteles.aulaifood.presentation.ui.adapter.LojasAdapter
import com.danilloteles.aulaifood.presentation.ui.adapter.ProdutoAdapter

class LojaFragment : Fragment() {

   private lateinit var binding: FragmentLojaBinding
   private lateinit var produtoAdapter: ProdutoAdapter
   private lateinit var produtoAdapterDestaque: ProdutoAdapter
   private val produtosDestaque = listOf(
      Produto("Chicken Méqui Box - 3 Mcofertas Médias",
         "São 3 Mcofertas para você compartilhar com quem preferir. Escolha 3 (três) sanduíches entre as opções: McChicken e3 (três) Bebidas.",
         "R$ 129,90", "R$ 89,90",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_8b0y5alue7h.png"),
      Produto("4 Chicken Junior + Mega McNuggets + 2 Bebidas",
         "Ótima opção para você compartilhar com quem preferir. Nesta promoção você leva, 4 (quatro) Chicken Junior, 15 (quinze) Chicken McNuggets e 2 (duas) Bebidas.",
         "R$ 99,90","R$ 59,90",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_azh1guqsavt.png"),
      Produto("McNuggets Méqui Box - 15 McNuggets + 3 Bebidas + 3 molhos",
         "Nesta promoção você leva: 15 (quinze) unidades de Chicken McNuggets + 3 (três) Bebidas de sua preferência + 3 (três) molhos de sua preferência. Imagem Meramente Ilustrativa",
         "R$ 156,90", "R$ 129,90",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_ypbmlfq0ou.png"),
      Produto("McOferta Média Chickens",
         "Nesta promoção você leva 1 McOferta Média entre as seguintes opções: McChicken, McChicken Bacon, Mccrispy Chicken Deluxe, Mccrispy Chicken Legend, Mccrispy Chicken Elite e Mccrispy Chicken Melt & Bacon.",
         "R$ 33,90", "R$ 29,90",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_o9ychrwxsd.png"),
      Produto("2x1 Chicken McNuggets 6 unidades",
         "Nesta promoção você leva 2 (dois) pacotes de Chicken McNuggets com 6 (seis) unidades cada. Crocantes, leves e deliciosos. Os frangos empanados mais irresistíveis do Mcdonald’s.",
         "R$ 19,90", "R$ 14,00",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_lq14eozbb5.png"),
   )
   private val produtos = listOf(
      Produto("Chicken Méqui Box - 3 Mcofertas Médias",
         "São 3 Mcofertas para você compartilhar com quem preferir #Méquinosofá. ",
         "R$ 129,90", "R$ 89,90",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_8b0y5alue7h.png"),
      Produto("4 Chicken Junior + Mega McNuggets + 2 Bebidas",
         "Ótima opção para você compartilhar com quem preferir.",
         "R$ 99,90","R$ 59,90",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_azh1guqsavt.png"),
      Produto("McNuggets Méqui Box - 15 McNuggets + 3 Bebidas + 3 molhos",
         "Nesta promoção você leva: 15 (quinze) unidades de Chicken McNuggets + 3 (três) Bebidas",
         "R$ 156,90", "R$ 129,90",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_ypbmlfq0ou.png"),
      Produto("McOferta Média Chickens",
         "Nesta promoção você leva 1 McOferta Média entre as seguintes opções: McChicken, McChicken Bacon...",
         "R$ 33,90", "R$ 29,90",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_o9ychrwxsd.png"),
      Produto("2x1 Chicken McNuggets 6 unidades",
         "Nesta promoção você leva 2 (dois) pacotes de Chicken McNuggets com 6 (seis) unidades cada.",
         "R$ 19,90", "R$ 14,00",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_lq14eozbb5.png"),
   )

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

   private fun inicializarProdutosDestaque() {
      with( binding ){
         val orientacao = RecyclerView.HORIZONTAL
         produtoAdapterDestaque = ProdutoAdapter(orientacao){ produto ->
            val navController = findNavController()
            navController.navigate(R.id.action_lojaFragment_to_produtoFragment)
         }
         produtoAdapterDestaque.adicionarLista( produtosDestaque )
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
            val navController = findNavController()
            navController.navigate(R.id.action_lojaFragment_to_produtoFragment)
         }
         produtoAdapter.adicionarLista( produtos )
         rvProdutosLoja.adapter = produtoAdapter
         rvProdutosLoja.layoutManager = LinearLayoutManager(
            context, orientacao, false
         )
      }
   }

   private fun inicializarEventosClique() {
      binding.btnLojaVoltar.setOnClickListener {
         val navController = findNavController()
         navController.navigate(R.id.homeFragment)
      }
   }

}