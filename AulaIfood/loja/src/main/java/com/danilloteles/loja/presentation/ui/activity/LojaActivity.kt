package com.danilloteles.loja.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.navegarPara
import com.danilloteles.loja.R
import com.danilloteles.loja.databinding.ActivityCadastroBinding
import com.danilloteles.loja.databinding.ActivityLoginBinding
import com.danilloteles.loja.databinding.ActivityLojaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LojaActivity : AppCompatActivity() {

   private val binding by lazy {
      ActivityLojaBinding.inflate(layoutInflater)
   }
   private val alertaCarregamento by lazy {
      AlertaCarregamento(this)
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)
      inicializar()
   }

   private fun inicializar() {
      inicializarEventosClique()
      inicializarObservaveis()
   }

   private fun inicializarEventosClique() {
      with(binding){
         btnLojaVoltar.setOnClickListener{
            navegarPara(HomeActivity::class.java)
         }
         btnSelecionarImagemCapa.setOnClickListener {  }
         btnSelecionarImagemPerfil.setOnClickListener {  }
         btnAtualizar.setOnClickListener {  }
      }
   }

   private fun inicializarObservaveis() {

   }
}