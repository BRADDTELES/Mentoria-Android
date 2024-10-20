package com.application.aulasqliteandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.application.aulasqliteandroid.database.DatabaseHelper
import com.application.aulasqliteandroid.databinding.ActivityMainBinding
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
        }

    }

    private fun listar() {

        try {
            bancoDados.readableDatabase.execSQL(
                "SELECT * FROM produtos;"
            )
            Log.i("info_db", "Sucesso ao listar produtos")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao listar produtos")
        }
    }

    private fun salvar(){
        val titulo = binding.editProduto.text.toString()
        val sql = "INSERT INTO produtos VALUES(null, '$titulo', 'Descrição...');"
        try {
            bancoDados.writableDatabase.execSQL( sql )
            Log.i("info_db", "Sucesso ao inserir")
        }catch (e: Exception){
            Log.i("info_db", "Erro ao inserir")
        }
    }
}