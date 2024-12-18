package com.application.primeiroprojeto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sortear(view: View){
        val textoResultado = findViewById<TextView>(R.id.text_resultado) // encontre um item de interface pelo ID
        val numero = Random().nextInt(11) // 0...10
        textoResultado.setText("número gerado $numero")
    }

}