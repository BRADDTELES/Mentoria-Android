package com.application.aulainjecaodependenciashilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulainjecaodependenciashilt.classes.Carro
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var carro: Carro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        carro.ligar()
    }
}