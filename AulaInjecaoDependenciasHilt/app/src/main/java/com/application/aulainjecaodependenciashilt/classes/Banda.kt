package com.application.aulainjecaodependenciashilt.classes

import android.util.Log

class Banda(
    val violao: Instrumento,
    val bateria: Instrumento
) {

    fun tudoSendoTocado(){
        Log.i("teste_hilt", "Banda tocando")
        violao.sendoTocado()
        bateria.sendoTocado()
    }
}