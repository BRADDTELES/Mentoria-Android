package com.danilloteles.aulaifood.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.danilloteles.aulaifood.R
import com.danilloteles.aulaifood.databinding.FragmentLojaBinding
import com.danilloteles.aulaifood.databinding.FragmentProdutoBinding

class ProdutoFragment : Fragment() {

   private lateinit var binding: FragmentProdutoBinding

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

      return binding.root
   }

   private fun inicializarOpcionais() {

   }

}