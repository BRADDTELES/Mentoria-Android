package com.app.revisaoaprendakotlinzero

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/*
Resumão Aprenda Kotlin do zero
+ Variáveis e constantes
+ Null Safety
+ Operadores aritiméticos, relacionais e lógicos
+ if else when
+ Arrays & Loops: while e for
+ Funções e parâmetros
+ Classes, objetos, atributos e métodos
+ Sobrecarga métodos
+ Modificadores de acesso (public, private, protected)
+ Herança
+ Interface
+ Companion Object
+ Lateinit
* */

class MainActivity : AppCompatActivity() {

    val nome: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println( nome?.length )

    }
}