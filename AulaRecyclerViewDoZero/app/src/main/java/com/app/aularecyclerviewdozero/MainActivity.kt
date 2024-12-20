package com.app.aularecyclerviewdozero

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvConversas: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvConversas = findViewById(R.id.rv_conversas)

        //Adaptador de dados
        rvConversas.adapter = ConversasAdapter()

        //Gerenciador de Layout
        rvConversas.layoutManager = LinearLayoutManager(this)

    }
}