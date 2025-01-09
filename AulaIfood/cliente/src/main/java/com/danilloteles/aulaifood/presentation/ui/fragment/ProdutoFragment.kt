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
import com.danilloteles.aulaifood.databinding.FragmentLojaBinding
import com.danilloteles.aulaifood.databinding.FragmentProdutoBinding
import com.danilloteles.aulaifood.databinding.ItemRvOpcionaisBinding
import com.danilloteles.aulaifood.domain.model.Opcional
import com.danilloteles.aulaifood.presentation.ui.adapter.OpcionaisAdapter
import com.danilloteles.aulaifood.presentation.ui.adapter.ProdutoAdapter

class ProdutoFragment : Fragment() {

   private lateinit var binding: FragmentProdutoBinding
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

   private fun inicializarOpcionais() {
      with( binding ){
         opcionaisAdapter = OpcionaisAdapter()
         opcionaisAdapter.adicionarItens( opcionais )
         rvOpcionaisProdutoDetlahe.adapter = opcionaisAdapter
         rvOpcionaisProdutoDetlahe.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL, false
         )
      }
   }

   private fun inicializarEventosClique() {
      binding.btnProdutoVoltar.setOnClickListener {
         val navController = findNavController()
         navController.navigate(R.id.lojaFragment)
      }
   }

}