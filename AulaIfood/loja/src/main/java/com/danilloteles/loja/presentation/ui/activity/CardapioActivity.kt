package com.danilloteles.loja.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danilloteles.aulaifood.domain.model.Produto
import com.danilloteles.loja.databinding.ActivityCardapioBinding
import com.danilloteles.loja.presentation.ui.adapter.ProdutoAdapter

class CardapioActivity : AppCompatActivity() {

   private val binding by lazy {
         ActivityCardapioBinding.inflate( layoutInflater )
   }

   private lateinit var produtoAdapter: ProdutoAdapter

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

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
   }

   private fun inicializar() {
      inicializarProdutos()
      inicializarToolbar()
   }

   private fun inicializarProdutos() {
      with( binding ){
         produtoAdapter = ProdutoAdapter(
            {}, {}, {}
         )
         produtoAdapter.adicionarLista( produtos )
         rvCardapio.adapter = produtoAdapter
         rvCardapio.layoutManager = LinearLayoutManager(
            applicationContext, RecyclerView.VERTICAL, false
         )
      }
   }

   private fun inicializarToolbar() {
      val toolbar = binding.includeTbCardapio.tbPrincipalLoja
      setSupportActionBar(toolbar)

      supportActionBar?.apply {
         title = "Cardápio da produtos"
         setDisplayHomeAsUpEnabled(true)
      }
   }
}