package com.application.aulainjecaodependenciashilt.classes

import android.util.Log

class Musico(
    private val instrumento: Instrumento
) {
    fun tocar(){
        Log.i("teste_hilt", "Musico tocado")
        instrumento.sendoTocado()
    }
}

interface Instrumento{
    fun sendoTocado()
}

class Violao() : Instrumento{
    override fun sendoTocado(){
        Log.i("teste_hilt", "Violao sendo tocado")
    }
}

class Bateria() : Instrumento{
    override fun sendoTocado(){
        Log.i("teste_hilt", "Bateria sendo tocado")
    }
}