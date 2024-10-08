package com.application.aulaactivityfragment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalhesActivity : AppCompatActivity() {

    lateinit var buttonFechar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        buttonFechar = findViewById( R.id.button_fechar )
        buttonFechar.setOnClickListener {
            finish()//Finalizar
            /* Também posso usar esses comandos abaixo
            val intent = Intent(
                this,
                MainActivity::class.java
            )

            //Iniciar uma nova tela
            startActivity( intent )*/
        }

    }
}