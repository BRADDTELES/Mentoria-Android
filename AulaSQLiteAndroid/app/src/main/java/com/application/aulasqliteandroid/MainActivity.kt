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

        val sql = "SELECT * FROM ${DatabaseHelper.TABELA_PRODUTOS};"
        val cursor = bancoDados.readableDatabase
            .rawQuery( sql, null )
        // cursor.moveToFirst() // Colocar o cursor no primeiro registro
        // cursor.moveToNext() // Avançar para o próximo registro

        val indiceId = cursor.getColumnIndex( "${DatabaseHelper.ID_PRODUTO}" )
        val indiceTitulo = cursor.getColumnIndex( "${DatabaseHelper.TITULO}" )
        val indiceDescricao = cursor.getColumnIndex( "${DatabaseHelper.DESCRICAO}" )

        while ( cursor.moveToNext() ){

            val idProduto = cursor.getInt(indiceId)
            val titulo = cursor.getString(indiceTitulo)
            val descricao = cursor.getString(indiceDescricao)
            Log.i("info_db", "id: $idProduto - $titulo")
        }
    }

    private fun salvar(){
        val titulo = binding.editProduto.text.toString()
        val sql = "INSERT INTO ${DatabaseHelper.TABELA_PRODUTOS} VALUES(null, '$titulo', 'Descrição...');"
        try {
            bancoDados.writableDatabase.execSQL( sql )
            Log.i("info_db", "Sucesso ao inserir")
        }catch (e: Exception){
            Log.i("info_db", "Erro ao inserir")
        }
    }
}