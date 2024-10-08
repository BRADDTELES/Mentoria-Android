package com.application.aulaactivityfragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var buttonAbrir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo_vida", "onCreate")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }

        buttonAbrir = findViewById( R.id.button_abrir )
        buttonAbrir.setOnClickListener {
            //finish()
            val intent = Intent(
                this,
                DetalhesActivity::class.java
            )
            //Passar parâmetros para nova tela
            /*intent.putExtra("filme", "The Witcher")
            intent.putExtra("classificação", 5)
            intent.putExtra("avaliação", 9.2)*/

            val filme = Filme(
                nome = "Sem Limites",
                descricao = "teste",
                avaliacao = 4.8,
                diretor = "Danillo",
                distribuidor = "Netflix"
            )
            intent.putExtra("filme", filme)


            //Iniciar uma nova tela
            startActivity( intent )

        }
    }

    /*override fun onStart() {
        super.onStart()
        Log.i("ciclo_vida", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo_vida", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo_vida", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo_vida", "onStop")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo_vida", "onRestart")
    }

    override fun onDestroy() {
        Log.i("ciclo_vida", "onDestroy")
        super.onDestroy()
    }*/
}