package com.danilloteles.aulaifood.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.danilloteles.aulaifood.R
import com.danilloteles.aulaifood.databinding.FragmentHomeBinding
import com.danilloteles.aulaifood.databinding.FragmentLojaBinding

class LojaFragment : Fragment() {

   private lateinit var binding: FragmentLojaBinding

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {

      binding = FragmentLojaBinding.inflate(
         inflater,
         container,
         false
      )

      binding.btnLojaVoltar.setOnClickListener {
         val navController = findNavController()
         navController.navigate(R.id.homeFragment)
      }

      return binding.root
   }

}