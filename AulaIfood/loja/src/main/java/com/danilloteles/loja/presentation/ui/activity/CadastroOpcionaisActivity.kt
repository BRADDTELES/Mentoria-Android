package com.danilloteles.loja.presentation.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danilloteles.loja.databinding.ActivityCadastroOpcionaisBinding
import com.danilloteles.loja.domain.model.Opcional
import com.danilloteles.loja.presentation.ui.adapter.OpcionaisAdapter
import com.danilloteles.loja.presentation.viewmodel.AutenticacaoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroOpcionaisActivity : AppCompatActivity() {

   private val binding by lazy {
      ActivityCadastroOpcionaisBinding.inflate(layoutInflater)
   }
   private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()
   private val opcionais = listOf(
      Opcional(
         "Molho Tasty",
         "Composto por um hambúrguer de carne 100% bovina",
         "R$ 3,00",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030416_204bpeti9ba.png"
      ),
      Opcional(
         "Cebola Fresca",
         "Composto por um hambúrguer de carne 100% bovina",
         " R$ 2,00",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202410150806_uc1qzud8yyf.png"
      ),
      Opcional(
         "Carne 100% Bovina",
         "Composto por um hambúrguer de carne 100% bovina",
         "R$ 9,00",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202410150806_3lrri4k5ijb.png"
      ),
      Opcional(
         "Maionese",
         "Composto por um hambúrguer de carne 100% bovina",
         "R$ 3,00",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_0iotkucm6s7u.png"
      ),
      Opcional(
         "Fatia Queijo Cheddar",
         "Composto por um hambúrguer de carne 100% bovina",
         " R$ 9,00",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202410150807_mruwic5r0ye.png"
      ),
      Opcional(
         "Bacon",
         "Composto por um hambúrguer de carne 100% bovina",
         "R$ 3,00",
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202410150807_lrnvvpb33ag.png"
      ),
   )
   private lateinit var opcionaisAdapter: OpcionaisAdapter

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
   }

   private fun inicializar() {
      inicializarToolbar()
      inicializarOpcionais()
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