package com.app.aulareceitasvo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvReceitas: RecyclerView
    private lateinit var receitasAdapter: ReceitasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvReceitas = findViewById(R.id.rv_receitas)

        val lista = listOf(
            Receita("Escondidinho de camarão", "25min", R.drawable.carne1),
            Receita("Panqueca de carne moída", "15min", R.drawable.carne2),
            Receita("Rocambole de carne moída", "30min", R.drawable.carne3),
            Receita("Escondidinho de carne seca", "45min", R.drawable.carne4),
        )

        //Adapter
        receitasAdapter = ReceitasAdapter{
            val intent = Intent(this, DetalhesActivity::class.java)
            startActivity( intent )
        }
        rvReceitas.adapter = receitasAdapter
        receitasAdapter.configurarLista( lista )

        //Layout
        rvReceitas.layoutManager = LinearLayoutManager(this)

    }
}