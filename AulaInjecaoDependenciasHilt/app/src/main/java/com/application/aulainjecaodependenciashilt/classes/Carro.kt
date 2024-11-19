package com.application.aulainjecaodependenciashilt.classes

import android.content.Context
import android.util.Log
import android.widget.Toast

class Carro( 
    val motor: Motor ,
    val context: Context
) { // Injeção de dependencia "Baixo acoplamento"
//class Carro {

    //lateinit var motor: Motor

    fun ligar(){
        //val motor = Motor()//Alto acomplamento "não recomendado"
        //println("Carro ligado")
        motor.acionar()
        Toast.makeText(context, "Carro ligado", Toast.LENGTH_SHORT).show()
        Log.i("teste_hilt", "Carro ligado")
    }
}