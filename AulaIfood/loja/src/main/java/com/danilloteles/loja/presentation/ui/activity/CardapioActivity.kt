package com.danilloteles.loja.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.exibirMensagem
import com.danilloteles.core.navegarPara
import com.danilloteles.loja.databinding.ActivityCardapioBinding
import com.danilloteles.loja.domain.model.Produto
import com.danilloteles.loja.presentation.ui.adapter.ProdutosAdapter
import com.danilloteles.loja.presentation.viewmodel.ProdutoViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardapioActivity : AppCompatActivity() {

   private val binding by lazy {
         ActivityCardapioBinding.inflate( layoutInflater )
   }
   private lateinit var produtosAdapter: ProdutosAdapter
   private val alertaCarregamento by lazy {
      AlertaCarregamento(this)
   }

   private val produtoViewModel: ProdutoViewModel by viewModels()
   private val produtos = emptyList<Produto>()

   /*private val produtos = listOf(
      Produto(
         "",
         "Chicken Méqui Box - 3 Mcofertas Médias",
         "São 3 Mcofertas para você compartilhar com quem preferir #Méquinosofá. ",
         89.90,
         29.90,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_8b0y5alue7h.png"),
      Produto(
         "",
         "4 Chicken Junior + Mega McNuggets + 2 Bebidas",
         "Ótima opção para você compartilhar com quem preferir.",
         99.90,
         59.90,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_azh1guqsavt.png"),
      Produto(
         "",
         "McNuggets Méqui Box - 15 McNuggets + 3 Bebidas + 3 molhos",
         "Nesta promoção você leva: 15 (quinze) unidades de Chicken McNuggets + 3 (três) Bebidas",
         156.90,
         129.90,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_ypbmlfq0ou.png"),
      Produto(
         "",
         "McOferta Média Chickens",
         "Nesta promoção você leva 1 McOferta Média entre as seguintes opções: McChicken, McChicken Bacon...",
         33.90,
         29.90,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_o9ychrwxsd.png"),
      Produto(
         "",
         "2x1 Chicken McNuggets 6 unidades",
         "Nesta promoção você leva 2 (dois) pacotes de Chicken McNuggets com 6 (seis) unidades cada.",
         19.90,
         14.00,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_lq14eozbb5.png"),
   )*/

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
   }

   override fun onStart() {
      super.onStart()
      recuperarProdutos()
   }

   private fun recuperarProdutos() {
      produtoViewModel.listar { uiStatus ->
         when ( uiStatus ) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               exibirMensagem( uiStatus.erro )
            }

            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               val listaProdutos = uiStatus.dados
               produtosAdapter.adicionarLista( listaProdutos )
            }

            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Carregando produtos")
            }
         }
      }
   }

   private fun inicializar() {
      inicializarRecyclerViewProdutos()
      inicializarToolbar()
      inicializarEventosClique()
   }


   private fun inicializarEventosClique() {
      with(binding){
         fabAdicionarProduto.setOnClickListener {
            navegarPara(CadastroProdutoActivity::class.java, false)
         }
      }
   }

   private fun inicializarRecyclerViewProdutos() {
      with( binding ){
         produtosAdapter = ProdutosAdapter(
            onClickOpcional = { produto ->
               val intent = Intent(applicationContext, CadastroOpcionaisActivity::class.java)
               intent.putExtra("produto", produto )
               startActivity(intent)
            },
            onClickEditar = { produto ->
               val intent = Intent(applicationContext, CadastroProdutoActivity::class.java)
               intent.putExtra("idProduto", produto.id)
               startActivity(intent)
            },
            onClickRemover = { produto ->
               exibirConfirmacaoRemocao( produto )
            }
         )
         produtosAdapter.adicionarLista( produtos )
         rvCardapio.adapter = produtosAdapter
         rvCardapio.layoutManager = LinearLayoutManager(
            applicationContext, RecyclerView.VERTICAL, false
         )
      }
   }

   private fun exibirConfirmacaoRemocao( produto: Produto ) {
      MaterialAlertDialogBuilder(this)
         .setTitle("Confirmar exclusão do produto?")
         .setMessage("Produto: ${produto.nome}\nPreço: ${produto.preco}")
         .setPositiveButton("Confirmar exclusão"){ dialog, _ ->
            removerProduto( produto.id )//Remover produto pelo o id
         }
         .setNegativeButton("Cancelar"){ dialog, _ ->
            dialog.dismiss()//Fechar a tela de cancelamento
         }
         .show()//Para poder fazer a exibição da tela
   }

   private fun removerProduto( idProduto: String ) {
      produtoViewModel.remover( idProduto ){ uiStatus ->
         when ( uiStatus ) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()//Fechar o Alerta de Carregamento quando Exibir o Erro
               exibirMensagem( uiStatus.erro )
            }
            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()////Fechar o Alerta de Carregamento quando der Sucesso
               recuperarProdutos()//Após der Sucesso na Remoção do produto, essa linha recupera todos os outros Produtos
               exibirMensagem("Produto removido com sucesso.")
            }
            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Removendo produto")
            }
         }
      }
   }

   private fun inicializarToolbar() {
      val toolbar = binding.includeTbCardapio.tbPrincipalLoja
      setSupportActionBar( toolbar )

      supportActionBar?.apply {
         title = "Cardápio de produtos"
         setDisplayHomeAsUpEnabled(true)
      }
   }

}