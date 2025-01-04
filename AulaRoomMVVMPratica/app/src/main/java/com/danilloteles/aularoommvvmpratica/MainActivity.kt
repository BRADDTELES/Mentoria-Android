package com.danilloteles.aularoommvvmpratica

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aularoommvvmpratica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        inicializarBarraNavegacao()

    }

    private fun inicializarBarraNavegacao() {

        addMenuProvider( object  : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_principal, menu)
                val itemPesquisa = menu.findItem( R.id.item_pesquisa)
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