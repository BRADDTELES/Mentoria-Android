package com.danilloteles.aulaifood.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.danilloteles.aulaifood.R
import com.danilloteles.aulaifood.data.remote.dto.CategoriaDTO
import com.danilloteles.aulaifood.databinding.FragmentHomeBinding
import com.danilloteles.aulaifood.presentation.adapter.CategoriaAdapter

class HomeFragment : Fragment() {

   private lateinit var binding: FragmentHomeBinding
   private lateinit var categoriaAdapter: CategoriaAdapter

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {

      binding = FragmentHomeBinding.inflate(
         inflater,
         container,
         false
      )

      inicializarNotificacoes()
      inicializarComponentesInterfaceCategorias()

      /*binding.textLoja.setOnClickListener {
         val navController = findNavController()
         navController.navigate(R.id.lojaFragment)
      }*/

      return binding.root
   }

   private fun inicializarComponentesInterfaceCategorias() {

      with( binding ){
         categoriaAdapter = CategoriaAdapter()
         rvFiltroCategorias.adapter = categoriaAdapter
         rvFiltroCategorias.layoutManager = GridLayoutManager(
            context,4
         )
         categoriaAdapter.adicionarLista(
            listOf(
               CategoriaDTO(R.drawable.restaurante,"Restaurante"),
               CategoriaDTO(R.drawable.mercado,"Mercado"),
               CategoriaDTO(R.drawable.farmacia,"Farmacia"),
               CategoriaDTO(R.drawable.pet,"Pet"),
               CategoriaDTO(R.drawable.expresso,"Expresso"),
               CategoriaDTO(R.drawable.bebida,"Bebidas"),
               CategoriaDTO(R.drawable.shopping,"Shopping"),
               CategoriaDTO(R.drawable.gourmet,"Gourmet"),
            )
         )
      }

   }

   private fun inicializarNotificacoes() {

      val menuItem = binding.tbHome.menu.findItem( R.id.item_notificacao )
      val textTotalNotificacoes = menuItem
         .actionView?.findViewById<TextView>(R.id.textTotalNotificacoes)

      textTotalNotificacoes?.text = "6"
   }

}