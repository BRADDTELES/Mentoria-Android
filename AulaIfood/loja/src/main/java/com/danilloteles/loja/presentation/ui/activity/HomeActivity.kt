package com.danilloteles.loja.presentation.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import com.danilloteles.core.navegarPara
import com.danilloteles.loja.R
import com.danilloteles.loja.databinding.ActivityHomeBinding
import com.danilloteles.loja.presentation.viewmodel.AutenticacaoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

   private val binding by lazy {
      ActivityHomeBinding.inflate(layoutInflater)
   }
   private val autenticacaoViewModel: AutenticacaoViewModel by viewModels()

   /* Se caso usar o @InstallIn(SingletonComponent::class) no AppModule.kt:
   * preciso Ativar ou descomentar essa Injeção de dependência abaixo,
   *
   *  Caso a não utilização: deve usar o @InstallIn(ViewModelComponent::class)
   * no AppModule.kt e Desative ou comente essa injeção abaixo
   */
   //@Inject lateinit var firebaseAuth: FirebaseAuth

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)
      inicializar()
   }

   private fun inicializar() {
      inicializarToolbar()
      incializarMenus()
   }

   private fun incializarMenus() {

      addMenuProvider(object : MenuProvider {
         override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.menu_home, menu)
         }

         override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

            when (menuItem.itemId) {
               R.id.item_gerenciamento_loja -> {
                  navegarPara( LojaActivity::class.java )
               }

               R.id.item_cardapio -> {
                  navegarPara( CardapioActivity::class.java, false )
               }
               R.id.item_taxa_endereco -> {}
               R.id.item_sair -> {
                  autenticacaoViewModel.deslogarUsuario()
                  navegarPara( LoginActivity::class.java )
               }
            }
            return true
         }

      })
   }

   private fun inicializarToolbar() {
      val toolbar = binding.includeTbHome.tbPrincipalLoja
      setSupportActionBar(toolbar)

      supportActionBar?.apply {
         title = "Gerenciamento de Loja"
      }
   }
}