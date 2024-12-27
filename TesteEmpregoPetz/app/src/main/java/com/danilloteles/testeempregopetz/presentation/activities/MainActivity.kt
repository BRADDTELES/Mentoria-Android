package com.danilloteles.testeempregopetz.presentation.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.testeempregopetz.R
import com.danilloteles.testeempregopetz.presentation.viewmodels.CartoesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val cartoesViewModel: CartoesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cartoesViewModel.listaCartas.observe(this){ listaCartas ->
            var lista = ""
            listaCartas.forEach { carta ->
                lista += " ${carta.cardId} \n"
            }
            Log.i("mensagem_api_cartas", lista )
        }
    }

    override fun onStart() {
        super.onStart()
        cartoesViewModel.recuperarCartoes()
    }
}