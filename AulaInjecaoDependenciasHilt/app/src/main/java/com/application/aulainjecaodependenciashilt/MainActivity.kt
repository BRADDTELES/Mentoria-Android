package com.application.aulainjecaodependenciashilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulainjecaodependenciashilt.classes.Banda
import com.application.aulainjecaodependenciashilt.classes.Carro
import com.application.aulainjecaodependenciashilt.classes.Musico
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //@Inject lateinit var carro: Carro
    //@Inject lateinit var musico: Musico
    @Inject lateinit var banda: Banda

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //carro.ligar()
        //musico.tocar()
        banda.tudoSendoTocado()
    }
}