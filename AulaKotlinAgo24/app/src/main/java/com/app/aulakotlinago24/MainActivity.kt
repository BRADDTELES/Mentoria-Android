package com.app.aulakotlinago24

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //lateinit -> inicialização tardia -> Carrega recursos que só podem ser executado quando onCreate for chamado dos ( Preferencias )
    /* lateinit var é um modificador em Kotlin
    que permite que você declare uma propriedade não nula que será inicializada mais tarde */
    lateinit var bancoDados: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}