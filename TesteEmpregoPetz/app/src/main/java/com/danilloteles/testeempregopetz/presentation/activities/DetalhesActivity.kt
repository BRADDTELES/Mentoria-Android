package com.danilloteles.testeempregopetz.presentation.activities

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.testeempregopetz.data.model.Carta
import com.danilloteles.testeempregopetz.databinding.ActivityDetalhesBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class DetalhesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
        recuperarDetalhesCarta()
    }

    //@SuppressLint("NewApi")
    private fun recuperarDetalhesCarta() {

        val bundle = intent.extras
        //val carta = bundle?.getParcelable("carta", Carta::class.java)
        val carta = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle?.getParcelable("carta", Carta::class.java)
        }else{
            bundle?.getParcelable("carta")
        }

        if (carta != null) {
            //Log.i("mensagem_api_carta", "carta: ${carta.cardId}")
            with( binding ){
                Picasso.get()
                    .load( carta.img )
                    .into( imageUrl )

                textId.text = carta.cardId
                textName.text = carta.name
                textSet.text = carta.cardSet
                textAttack.text = carta.attack.toString()
                textCost.text = carta.cost.toString()
                textHealth.text = carta.health.toString()
                textClass.text = carta.playerClass
                textRace.text = carta.race
                textDescription.text = carta.text
                textType.text = carta.type

            }

        }


    }
}