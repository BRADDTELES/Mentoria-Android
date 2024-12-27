package com.danilloteles.testeempregopetz.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.testeempregopetz.R
import com.danilloteles.testeempregopetz.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
    }
}