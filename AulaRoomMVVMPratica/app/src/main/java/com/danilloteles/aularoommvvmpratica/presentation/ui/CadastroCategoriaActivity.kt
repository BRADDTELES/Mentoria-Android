package com.danilloteles.aularoommvvmpratica.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.aularoommvvmpratica.databinding.ActivityCadastroCategoriaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroCategoriaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroCategoriaBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
    }
}