package com.application.aulasqliteandroid.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.sql.SQLException

class DatabaseHelper(context: Context): SQLiteOpenHelper(
    //1) Contexto 2) Nome do banco de dados
    //3) CursorFactory 4) versão do banco de dados
    context,"loja.db", null, 2
) {

    companion object{
        const val TABELA_PRODUTOS = "produtos"
        const val ID_PRODUTO = "id_produto"
        const val TITULO = "titulo"
        const val DESCRICAO = "descricao"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Criar Tabelas
        Log.i("info_db", "Executou onCreate")
        val sql = "CREATE TABLE IF NOT EXISTS $TABELA_PRODUTOS (" +
                "$ID_PRODUTO INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "$TITULO VARCHAR(100), " +
                "$DESCRICAO TEXT" +
                ");"
        try {
            db?.execSQL( sql )
            Log.i("info_db", "Sucesso ao criar a tabela")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao criar a tabela")
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Atualizar o banco de dados
        Log.i("info_db", "Executou onUpgrade")
    }
}