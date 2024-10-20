package com.application.aulasqliteandroid.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(
    //1) Contexto 2) Nome do banco de dados
    //3) CursorFactory 4) versão do banco de dados
    context,"loja.db", null, 1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        // Criar Tabelas


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}