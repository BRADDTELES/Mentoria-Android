package com.application.aulasqliteandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.application.aulasqliteandroid.database.DatabaseHelper

class MainActivity : AppCompatActivity() {

    private val bancoDados by lazy {
        DatabaseHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            bancoDados.writableDatabase.execSQL(
                "INSERT INTO produtos VALUES(null, 'Notebook Acer', 'Descrição...');"
            )
            Log.i("info_db", "Sucesso ao inserir")
        }catch (e: Exception){
            Log.i("info_db", "Erro ao inserir")
        }

    }
}