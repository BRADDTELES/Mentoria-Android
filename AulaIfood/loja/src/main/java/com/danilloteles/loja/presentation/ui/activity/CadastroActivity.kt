package com.danilloteles.loja.presentation.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.esconderTeclado
import com.danilloteles.core.exibirMensagem
import com.danilloteles.core.navegarPara
import com.danilloteles.loja.R
import com.danilloteles.loja.databinding.ActivityCadastroBinding
import com.danilloteles.loja.domain.model.Usuario
import com.danilloteles.loja.presentation.viewmodel.AutenticacaoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroActivity : AppCompatActivity() {

   private val binding by lazy {
      ActivityCadastroBinding.inflate(layoutInflater)
   }
   private val alertaCarregamento by lazy {
      AlertaCarregamento(this)
   }
   private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)
      inicializar()
   }

   override fun onStart() {
      super.onStart()

   }

   private fun inicializar() {
      inicializarToolbar()
      inicializarEventosClique()
      inicializarObservaveis()
   }

   private fun inicializarObservaveis() {

      autenticacaoViewModel.carregando.observe(this) { carregando ->
         if (carregando) {
            alertaCarregamento.exibir("Fazendo seu cadastro!")
         } else {
            alertaCarregamento.fechar()
         }
      }

      autenticacaoViewModel.resultadoValidacao
         .observe(this) { resultadoValidacao ->

            with(binding) {

               editCadastroNome.error =
                  if (resultadoValidacao.nome) null else getString(R.string.erro_cadastro_nome)

               editCadastroEmail.error =
                  if (resultadoValidacao.email) null else getString(R.string.erro_cadastro_email)

               editCadastroSenha.error =
                  if (resultadoValidacao.senha) null else getString(R.string.erro_cadastro_senha)

               editCadastroTelefone.error =
                  if (resultadoValidacao.telefone) null else getString(R.string.erro_cadastro_telefone)

            }

         }

   }

   private fun inicializarEventosClique() {
      with(binding) {
         btnCadastrar.setOnClickListener { view ->

            //Esconder o Teclado
            view.esconderTeclado()

            //Remover Focus
            editCadastroNome.clearFocus()
            editCadastroEmail.clearFocus()
            editCadastroSenha.clearFocus()
            editCadastroTelefone.clearFocus()

            val nome = editCadastroNome.text.toString()
            val email = editCadastroEmail.text.toString()
            val senha = editCadastroSenha.text.toString()
            val telefone = editCadastroTelefone.text.toString()

            val usuario = Usuario(
               email, senha, nome, telefone
            )
            autenticacaoViewModel.cadastrarUsuario(usuario) { uiStatus ->
               when (uiStatus) {
                  is UIStatus.Sucesso -> {
                     navegarPara( HomeActivity::class.java )
                  }

                  is UIStatus.Erro -> {
                     exibirMensagem(uiStatus.erro)
                  }
                  is UIStatus.Carregando -> {}
               }
            }

         }
      }
   }

   private fun inicializarToolbar() {
      val toolbar = binding.includeTbPrincipal.tbPrincipalLoja
      setSupportActionBar(toolbar)

      supportActionBar?.apply {
         title = "Cadastro de Loja"
         setDisplayHomeAsUpEnabled(true)
      }
   }
}