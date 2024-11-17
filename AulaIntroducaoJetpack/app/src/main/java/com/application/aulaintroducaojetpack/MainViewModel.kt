package com.application.aulaintroducaojetpack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    private var contador = 0

    //Obersevável
    private val liveData = MutableLiveData<Int>()

    fun recuperarLiveData() : MutableLiveData<Int> {
        liveData.value = contador
        return liveData
    }

    fun incrementar(){
        contador++
        liveData.value = contador
    }
}