package com.danilloteles.aularoommvvmpratica.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.danilloteles.aularoommvvmpratica.R
import com.danilloteles.aularoommvvmpratica.data.database.BancoDados
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria
import com.danilloteles.aularoommvvmpratica.databinding.ActivityMainBinding
import com.danilloteles.aularoommvvmpratica.presentation.ui.adapter.AnotacaoAdapter
import com.danilloteles.aularoommvvmpratica.presentation.viewmodel.CategoriaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var anotacaoAdapter: AnotacaoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
        inicializarUI()
        inicializarBarraNavegacao()
        inicializarEventosClique()


        /*val categoriaDAO = bancoDados.categoriaDAO
        CoroutineScope( Dispatchers.IO ).launch {
            val categoria = Categoria(
                0, "Mercado"
            )
            categoriaDAO.salvar( categoria )
        }*/

    }

    private fun inicializarUI() {

        with( binding ){

            anotacaoAdapter = AnotacaoAdapter()
            rvAnotacoes.adapter = anotacaoAdapter
            rvAnotacoes.layoutManager = StaggeredGridLayoutManager(
                2, LinearLayoutManager.VERTICAL
            )

        }

    }

    private fun inicializarEventosClique() {

        binding.fabAdicionar.setOnClickListener {
            startActivity(
                Intent(this, CadastroAnotacaoActivity::class.java)
            )
        }

    }

    private fun inicializarBarraNavegacao() {

        addMenuProvider( object  : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_principal, menu)
                val itemPesquisa = menu.findItem(R.id.item_pesquisa)
                val searchView = itemPesquisa.actionView as SearchView

                searchView.queryHint = "Digite algo para pesquisar"
                searchView.setOnCloseListener {
                    Log.i("pesquisa_search", "Saiu do SearchView")
                    true
                }
                searchView.setOnQueryTextListener( object : OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        Log.i("pesquisa_search", "onQueryTextSubmit: $query")
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        Log.i("pesquisa_search", "onQueryTextChange: $newText")
                        return true
                    }

                })

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when( menuItem.itemId ){
                    R.id.item_pesquisa -> {
                        //Código
                        true
                    }
                    else -> true
                }
            }

        })

    }
}