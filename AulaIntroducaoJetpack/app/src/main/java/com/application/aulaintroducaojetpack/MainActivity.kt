package com.application.aulaintroducaojetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulaintroducaojetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        binding.btnClique.setOnClickListener {
            contador++
            binding.textResultado.text = "cliques: $contador"
        }

    }
}