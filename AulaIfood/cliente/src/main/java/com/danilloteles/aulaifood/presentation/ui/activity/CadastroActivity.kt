package com.danilloteles.aulaifood.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.aulaifood.R
import com.danilloteles.aulaifood.databinding.ActivityCadastroBinding
import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.aulaifood.presentation.viewmodel.AutenticacaoViewModel
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.esconderTeclado
import com.danilloteles.core.exibirMensagem
import com.danilloteles.core.navegarPara
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroActivity : AppCompatActivity() {

   private val binding by lazy {
      ActivityCadastroBinding.inflate( layoutInflater )
   }
   private val alertaCarregamento by lazy {
      AlertaCarregamento(this)
   }
   private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
   }

   private fun inicializar() {
      inicializarToolbar()
      inicializarEventosClique()
      inicializarObservaveis()
   }

   fun navegarTelaPrincipal() {
      startActivity(
         Intent(this, MainActivity::class.java)
      )
   }

   private fun inicializarObservaveis() {

      autenticacaoViewModel.carregando.observe(this){ carregando ->
         if ( carregando ) {
            alertaCarregamento.exibir("Fazendo seu cadastro!")
         }else{
            alertaCarregamento.fechar()
         }
      }

      autenticacaoViewModel.resultadoValidacao
         .observe(this){ resultadoValidacao ->

            with( binding ){

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
      with( binding ){
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
            autenticacaoViewModel.cadastrarUsuario( usuario ){ uiStatus ->
               when( uiStatus ){
                  is UIStatus.Sucesso -> {
                     navegarPara( MainActivity::class.java )
                  }
                  is UIStatus.Erro -> {
                     exibirMensagem( uiStatus.erro )
                  }
               }
            }

         }
      }
   }

   private fun inicializarToolbar() {
      val toolbar = binding.includeTbPrincipal.tbPrincipal
      setSupportActionBar( toolbar )

      supportActionBar?.apply {
         title = "Cadastro de usuário"
         setDisplayHomeAsUpEnabled(true)
      }
   }
}