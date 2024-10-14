package com.application.aulacomponenteslistagemcolecoes

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListViewActivity : AppCompatActivity() {

    private lateinit var listViewUsuarios: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val listaUsuarios = listOf(
            "jamilton", "ana", "maria", "pedro", "marcela"
        )

        listViewUsuarios = findViewById(R.id.list_usuarios)
        listViewUsuarios.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            listaUsuarios
        )

    }
}