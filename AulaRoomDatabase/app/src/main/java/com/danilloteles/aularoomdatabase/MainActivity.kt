package com.danilloteles.aularoomdatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aularoomdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        binding.btnSalvar.setOnClickListener {  }
        binding.btnRemover.setOnClickListener {  }
        binding.btnAtualizar.setOnClickListener {  }
        binding.btnListar.setOnClickListener {  }

    }
}