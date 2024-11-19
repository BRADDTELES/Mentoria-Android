package com.application.aulainjecaodependenciashilt

import android.util.Log

class Carro( val motor: Motor ) { // Injeção de dependencia "Baixo acoplamento"
//class Carro {

    //lateinit var motor: Motor

    fun ligar(){
        //val motor = Motor()//Alto acomplamento "não recomendado"
        //println("Carro ligado")
        motor.acionar()
        Log.i("teste_hilt", "Carro ligado")
    }
}