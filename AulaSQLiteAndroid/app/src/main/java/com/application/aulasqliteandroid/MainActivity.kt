package com.application.aulasqliteandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.application.aulasqliteandroid.database.DatabaseHelper
import com.application.aulasqliteandroid.database.ProdutoDAO
import com.application.aulasqliteandroid.databinding.ActivityMainBinding
import com.application.aulasqliteandroid.model.Produto
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val bancoDados by lazy {
        DatabaseHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        with(binding){

            btnSalvar.setOnClickListener {
                salvar()
            }

            btnListar.setOnClickListener {
                listar()
            }

            btnAtualizar.setOnClickListener {
                atualizar()
            }

            btnRemover.setOnClickListener {
                remover()
            }
        }

    }

    private fun remover() {

        val produtoDAO = ProdutoDAO(this)
        produtoDAO.remover( 8 )

    }

    private fun atualizar() {

        val titulo = binding.editProduto.text.toString()
        val produtoDAO = ProdutoDAO(this)
        val produto = Produto(
            8,
            titulo,
            "descrição..."
        )
        produtoDAO.atualizar( produto )

    }

    private fun listar() {

        val produtoDAO = ProdutoDAO(this)
        val listaProdutos = produtoDAO.listar()

        if ( listaProdutos.isNotEmpty() ){
            listaProdutos.forEach { produto ->
                Log.i("info_db", "${produto.idProduto} - ${produto.titulo}")
            }
        }

    }

    private fun salvar(){

        val titulo = binding.editProduto.text.toString()
        val produtoDAO = ProdutoDAO(this)
        val produto = Produto(
            -1,
            titulo,
            "descrição..."
        )
        produtoDAO.salvar( produto )

    }
}