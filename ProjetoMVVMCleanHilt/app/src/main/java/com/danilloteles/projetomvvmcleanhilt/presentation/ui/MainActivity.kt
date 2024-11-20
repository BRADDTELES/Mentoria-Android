package com.danilloteles.projetomvvmcleanhilt.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

//DTO -> Data Transfer Object (Objeto de Transferência de Dados) é como se fosse um Model
//Util -> Classe de utilitários que pode se chamar também de Helper
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

    }
}