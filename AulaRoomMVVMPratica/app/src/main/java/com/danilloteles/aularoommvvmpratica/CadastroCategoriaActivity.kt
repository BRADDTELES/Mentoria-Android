package com.danilloteles.aularoommvvmpratica

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aularoommvvmpratica.databinding.ActivityCadastroAnotacaoBinding
import com.danilloteles.aularoommvvmpratica.databinding.ActivityCadastroCategoriaBinding

class CadastroCategoriaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroCategoriaBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
    }
}