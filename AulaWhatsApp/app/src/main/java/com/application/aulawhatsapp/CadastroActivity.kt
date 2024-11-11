package com.application.aulawhatsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulawhatsapp.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}