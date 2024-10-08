package com.application.aulaactivityfragment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalhesActivity : AppCompatActivity() {

    lateinit var buttonFechar: Button
    lateinit var textFilme: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        buttonFechar = findViewById( R.id.button_fechar )
        textFilme = findViewById( R.id.textFilme )

        val bundle = intent.extras//todos os parâmetros passados
        if ( bundle != null ){
            val filme = bundle.getString("filme")
            val classificação = bundle.getInt("classificação")
            val avaliação = bundle.getDouble("avaliação")

            val resultado = "filme: $filme - class. $classificação - aval. $avaliação"
            textFilme.text = resultado
        }



        buttonFechar.setOnClickListener {
            finish()//Finalizar
            /*//Também posso usar esses comandos abaixo
            val intent = Intent(
                this,
                MainActivity::class.java
            )

            //Iniciar uma nova tela
            startActivity( intent )*/
        }

    }
}