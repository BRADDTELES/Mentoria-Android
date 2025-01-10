package com.danilloteles.aulaifood.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danilloteles.aulaifood.R
import com.danilloteles.aulaifood.databinding.FragmentBuscaBinding
import com.danilloteles.aulaifood.databinding.FragmentHomeBinding
import com.danilloteles.aulaifood.domain.model.Loja
import com.danilloteles.aulaifood.presentation.ui.adapter.LojasAdapter

class BuscaFragment : Fragment() {

   private lateinit var binding: FragmentBuscaBinding
   private lateinit var lojasAdapter: LojasAdapter

   private val lojas = listOf(
      Loja("MC Donalds","Lanche","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/e35a1e98-0584-4315-afcb-ad6c621ce28a/202408251240_PNLZ.png?imwidth=128"),
      Loja("Burger King","Lanche","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/b66d0e6b-465e-45db-af1c-02a625072c95/202408011950_8A10.png?imwidth=128"),
      Loja("Donuts À Bessa","Doces & Bolos","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/08a030c7-45fa-4d3c-8c37-e39d2d077e28/202005212131_L7YE_i.jpg?imwidth=128"),
      Loja("Açaí Emporio","Açaí","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/a66dae6c-5466-451a-826b-70babe4c246d/202412090818_M4Pi_i.jpg?imwidth=128"),
      Loja("Nobre Sorvetes","Sorvetes","https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/9e57165a-5c24-4b46-95ad-37cee420279a/202001071031_fBuz_i.png"),
      Loja("Panificadora Panini","Padaria","https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/606929b7-3b67-4116-9728-f486e3ec1d99/202003231200_HDzR_i.png"),
      Loja("QG","Brasileira","https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/3fa2e9ab-4aad-4423-89de-d1a89d4222fd/202412081334_J9fA_i.jpg"),
      Loja("Comida Caseira da Maria","Marmita","https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/b804bee2-74d4-4689-af5e-5ad863caabdb/202210171620_iypQ_i.jpg"),
      Loja("Parmeginando Delivery","Italiana","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/a1649f68-ef34-4afc-b0dd-7ec8562dfc1c/202408221249_71TG_i.jpg?imwidth=128"),
   )

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      binding = FragmentBuscaBinding.inflate(
         inflater,
         container,
         false
      )

      inicializarLojas()

      return binding.root
   }

   private fun inicializarLojas() {
      with( binding ){
         val orientacao = RecyclerView.VERTICAL
         lojasAdapter = LojasAdapter(orientacao){ loja ->
            val navController = findNavController()
            navController.navigate(R.id.action_buscaFragment_to_lojaFragment)
         }
         lojasAdapter.adicionarLista( lojas )
         rvBuscaLojas.adapter = lojasAdapter
         rvBuscaLojas.layoutManager = LinearLayoutManager(
            context, orientacao, false
         )
      }
   }

}