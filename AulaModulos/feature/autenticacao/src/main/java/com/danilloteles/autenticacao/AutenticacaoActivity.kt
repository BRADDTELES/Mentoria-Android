package com.danilloteles.autenticacao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.autenticacao.databinding.ActivityAutenticacaoBinding

class AutenticacaoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAutenticacaoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogar.setOnClickListener{
            finish()
        }

    }
}