package com.danilloteles.aulaifood.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.danilloteles.aulaifood.R
import com.danilloteles.aulaifood.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

   private lateinit var binding: FragmentHomeBinding

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

      /*binding.textLoja.setOnClickListener {
         val navController = findNavController()
         navController.navigate(R.id.lojaFragment)
      }*/

      return binding.root
   }

   private fun inicializarNotificacoes() {

      val menuItem = binding.tbHome.menu.findItem( R.id.item_notificacao )
      val textTotalNotificacoes = menuItem
         .actionView?.findViewById<TextView>(R.id.textTotalNotificacoes)

      textTotalNotificacoes?.text = "6"
   }

}