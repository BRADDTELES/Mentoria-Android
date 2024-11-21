package com.danilloteles.lojas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.validacoes.Valida

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Valida.validarEmail()

    }
}