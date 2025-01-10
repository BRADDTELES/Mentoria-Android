package com.danilloteles.loja.presentation.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.core.navegarPara
import com.danilloteles.loja.R
import com.danilloteles.loja.databinding.ActivityCadastroBinding
import com.danilloteles.loja.databinding.ActivityCadastroProdutoBinding
import com.danilloteles.loja.databinding.ActivityCardapioBinding
import com.danilloteles.loja.presentation.viewmodel.AutenticacaoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroProdutoActivity : AppCompatActivity() {

   private val binding by lazy {
      ActivityCadastroProdutoBinding.inflate( layoutInflater )
   }

   private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
   }

   private fun inicializar() {
      inicializarEventosClique()
   }

   private fun inicializarEventosClique() {
      with(binding){
         btnCadastroProdutoVoltar.setOnClickListener {
            navegarPara(CardapioActivity::class.java)
         }
      }
   }
}