package com.danilloteles.aulatesteapipratico.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.aulatesteapipratico.R
import com.danilloteles.aulatesteapipratico.data.remote.api.RetrofitCustom
import com.danilloteles.aulatesteapipratico.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializar()
    }

    private fun inicializar() {
        inicializarViews()
    }

    private fun inicializarViews() {

        val dummyAPI = RetrofitCustom.recuperarDumyAPI()

    }
}