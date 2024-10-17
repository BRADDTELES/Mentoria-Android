package com.application.aulacomponentesinterfaceviewbinding

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ToolbarActionbarActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar_actionbar)

        //supportActionBar?.hide()
        inicializarActionBar()

    }

    private fun inicializarActionBar() {

        addMenuProvider(
            object :  MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_principal, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when( menuItem.itemId ){
                        R.id.item_adicionar -> {
                            Toast.makeText(applicationContext,"Adicionar", Toast.LENGTH_SHORT).show()
                        }
                        R.id.item_pesquisar -> {
                            Toast.makeText(applicationContext, "Pesquisar", Toast.LENGTH_SHORT).show()
                        }
                        R.id.item_configuracoes -> {
                            Toast.makeText(applicationContext, "Configurações", Toast.LENGTH_SHORT).show()
                        }
                        R.id.item_sair -> {
                            Toast.makeText(applicationContext, "Sair", Toast.LENGTH_SHORT).show()
                        }
                    }
                    return true
                }

            }
        )

    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate( R.menu.menu_principal, menu )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when( item.itemId ){
            R.id.item_adicionar -> {
                Toast.makeText(this, "Adicionar", Toast.LENGTH_SHORT).show()
            }
            R.id.item_pesquisar -> {
                Toast.makeText(this, "Pesquisar", Toast.LENGTH_SHORT).show()
            }
            R.id.item_configuracoes -> {
                Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show()
            }
            R.id.item_sair -> {
                Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }*/
}