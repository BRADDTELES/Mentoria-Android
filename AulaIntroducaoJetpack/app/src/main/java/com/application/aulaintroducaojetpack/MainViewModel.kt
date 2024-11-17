package com.application.aulaintroducaojetpack

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    private var contador = 0

    fun eecuperarContador() : Int {
        return  contador
    }

    fun incrementar(){
        contador++
    }
}