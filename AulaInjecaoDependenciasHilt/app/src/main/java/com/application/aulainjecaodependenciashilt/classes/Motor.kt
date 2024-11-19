package com.application.aulainjecaodependenciashilt.classes

import android.util.Log

interface Motor {
    fun acionar()
}

class MotorGasolina() : Motor{
    override fun acionar(){
        Log.i("teste_hilt", "Motor ligado a gasolina")
    }
}

class MotorEletrico() : Motor{
    override fun acionar(){
        Log.i("teste_hilt", "Motor ligado eletrico")
    }
}