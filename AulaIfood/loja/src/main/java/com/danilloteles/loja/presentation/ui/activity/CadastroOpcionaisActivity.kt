package com.danilloteles.loja.presentation.ui.activity

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danilloteles.core.esconderTeclado
import com.danilloteles.core.exibirMensagem
import com.danilloteles.loja.databinding.ActivityCadastroOpcionaisBinding
import com.danilloteles.loja.domain.model.Opcional
import com.danilloteles.loja.domain.model.Produto
import com.danilloteles.loja.presentation.ui.adapter.OpcionaisAdapter
import com.jamiltondamasceno.core.adicionarMascaraMoeda
import com.jamiltondamasceno.core.formatarComoMoeda
import com.jamiltondamasceno.core.moedaToDouble
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroOpcionaisActivity : AppCompatActivity() {

   private val binding by lazy {
      ActivityCadastroOpcionaisBinding.inflate(layoutInflater)
   }
   private val opcionais = listOf(
      Opcional(
         "",
         "",
         "Molho Tasty",
         "Composto por um hambúrguer de carne 100% bovina",
         3.00,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030416_204bpeti9ba.png"
      ),
      Opcional(
         "",
         "",
         "Cebola Fresca",
         "Composto por um hambúrguer de carne 100% bovina",
         2.00,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202410150806_uc1qzud8yyf.png"
      ),
      Opcional(
         "",
         "",
         "Carne 100% Bovina",
         "Composto por um hambúrguer de carne 100% bovina",
         9.00,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202410150806_3lrri4k5ijb.png"
      ),
      Opcional(
         "",
         "",
         "Maionese",
         "Composto por um hambúrguer de carne 100% bovina",
         3.00,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_0iotkucm6s7u.png"
      ),
      Opcional(
         "",
         "",
         "Fatia Queijo Cheddar",
         "Composto por um hambúrguer de carne 100% bovina",
         9.00,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202410150807_mruwic5r0ye.png"
      ),
      Opcional(
         "",
         "",
         "Bacon",
         "Composto por um hambúrguer de carne 100% bovina",
         3.00,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202410150807_lrnvvpb33ag.png"
      ),
   )
   private lateinit var opcionaisAdapter: OpcionaisAdapter
   private var produto: Produto? = null
   private var urlOpcional: Uri? = null

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
   }

   private fun inicializar() {
      inicializarDadosProduto()
      inicializarToolbar()
      inicializarOpcionais()
      inicializarEventosClique()
   }

   private fun inicializarEventosClique() {

      with( binding ){
         btnSelecionarImagemOpcional.setOnClickListener {
            selecionarImagemOpcional.launch(
               PickVisualMediaRequest(
                  ActivityResultContracts.PickVisualMedia.ImageOnly
               )
            )
         }
         btnSalvarOpcional.setOnClickListener { view ->

            view.esconderTeclado()
            //Remover Focus
            editNomeOpcional.clearFocus()
            editDescricaoOpcional.clearFocus()
            editPrecoOpcional.clearFocus()

            val nome = editNomeOpcional.text.toString()
            val preco = editPrecoOpcional.moedaToDouble()
            val descricao = editDescricaoOpcional.text.toString()

            produto?.let { produto ->
               val opcional = Opcional(
                  idProduto = produto.id, nome = nome,
                  descricao = descricao, preco = preco
               )

               val resultadoValidacao = validaDadosOpcional(opcional)
               if ( resultadoValidacao ) {
                  salvarOpcional( opcional )
               }else{
                  exibirMensagem("Prencha os campos do produto")
               }
            }
         }
      }
   }

   private fun salvarOpcional( opcional: Opcional ) {



   }

   private fun validaDadosOpcional(opcional: Opcional): Boolean {
      if ( opcional.nome.isEmpty() ) return false
      if ( opcional.descricao.isEmpty() ) return false
      if ( opcional.preco == 0.0 ) return false
      if ( urlOpcional == null ) return false
      return true
   }

   private val selecionarImagemOpcional = registerForActivityResult(
      ActivityResultContracts.PickVisualMedia()
   ) { uri ->
      if (uri != null) {
         binding.imageCapaOpcional.setImageURI( uri )
         urlOpcional = uri
      } else {
         exibirMensagem("Nenhuma imagem foi selecionada para o produto!")
      }
   }

   private fun inicializarDadosProduto() {
      binding.editPrecoOpcional.adicionarMascaraMoeda()
      val bundle = intent.extras
      val produto = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
         bundle?.getParcelable("produto", Produto::class.java)
      } else {
         bundle?.getParcelable("produto")
      }

      produto?.let { produto ->
         binding.textDadosProduto.text = "${produto.nome} - ${produto.preco.formatarComoMoeda()}"
      }

   }

   private fun inicializarOpcionais() {
      with( binding ){
         opcionaisAdapter = OpcionaisAdapter{ opcional ->

         }
         opcionaisAdapter.adicionarItens( opcionais )
         rvOpcionais.adapter = opcionaisAdapter
         rvOpcionais.layoutManager = LinearLayoutManager(
            applicationContext, RecyclerView.VERTICAL, false
         )
      }
   }

   private fun inicializarToolbar() {
      val toolbar = binding.includeTbOpcionais.tbPrincipalLoja
      setSupportActionBar(toolbar)

      supportActionBar?.apply {
         title = "Adicionar opcionais"
         setDisplayHomeAsUpEnabled(true)
      }
   }
}