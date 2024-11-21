package com.danilloteles.aulamodulos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.api.Retrofit
import com.danilloteles.banco.BancoDadosApp
import com.danilloteles.validacoes.Valida

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Valida.validarData()
        Retrofit.configuracao()
        BancoDadosApp.configuracaoBanco()
    }
}