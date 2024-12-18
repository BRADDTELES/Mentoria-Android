package com.application.aulainjecaodependenciashilt.classes

import android.util.Log

class MotorEnergiaSolar : Motor {
    override fun acionar() {
        Log.i("teste_hilt", "Motor ligado utilizando energia solar")
    }
}