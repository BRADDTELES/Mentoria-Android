package com.danilloteles.aularoommvvmpratica

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aularoommvvmpratica.databinding.ActivityCadastroAnotacaoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroAnotacaoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroAnotacaoBinding.inflate( layoutInflater )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        binding.btnAdicionarCategoria.setOnClickListener {
            startActivity(
                Intent(this, CadastroCategoriaActivity::class.java)
            )
        }

    }
}