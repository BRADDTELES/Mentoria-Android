package com.application.auladebugger

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var textClique: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textClique = findViewById(R.id.textClique)
        textClique.setOnClickListener {
            val listaUsuarios = listOf("Danillo", "Ana", "Maria", "João")
            exibirListaItens ( listaUsuarios )
        }

    }

    private fun exibirListaItens(listaUsuarios: List<String>) {

        var exibirPrimeiroUsuario = true
        var contadorItens = 0 // Break Point (ponto de parada)

        for ( usuario in listaUsuarios ){
            if ( exibirPrimeiroUsuario ){
                println("primeiro usuário: ")
                exibirPrimeiroUsuario = false
            }
            exibirItem( usuario )
            contadorItens++
        }
    }

    private fun exibirItem(usuario: String) {
        println(usuario)
        println("--------")
    }
}