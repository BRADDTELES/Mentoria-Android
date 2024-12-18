package com.danilloteles.aulamodulos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.api.Retrofit
import com.danilloteles.aulamodulos.databinding.ActivityMainBinding
import com.danilloteles.autenticacao.AutenticacaoActivity
import com.danilloteles.banco.BancoDadosApp
import com.danilloteles.validacoes.Valida

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnAbrirLogin.setOnClickListener{
            startActivity(
                Intent(this, AutenticacaoActivity::class.java)
            )
        }

        /*Valida.validarData()
        Retrofit.configuracao()
        BancoDadosApp.configuracaoBanco()*/

    }
}