package com.danilloteles.aularoommvvmpratica.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria
import com.danilloteles.aularoommvvmpratica.databinding.ActivityCadastroCategoriaBinding
import com.danilloteles.aularoommvvmpratica.presentation.viewmodel.CategoriaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroCategoriaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroCategoriaBinding.inflate( layoutInflater )
    }
    private val categoriaViewModel: CategoriaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        inicializarListeners()
        inicializarObservables()

    }

    private fun inicializarObservables() {

        categoriaViewModel.resultadoOperacao.observe(this){ resultado ->
            if ( resultado.sucesso ) {
                Toast.makeText(this, resultado.mensagem, Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, resultado.mensagem, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun inicializarListeners() {
        with( binding ){
            btnSalvarCategoria.setOnClickListener {
                val nome = editNomeCategoria.text.toString()
                if ( nome.isNotEmpty() ) {
                    categoriaViewModel.salvar(
                        Categoria(
                            0, nome
                        )
                    )
                }else{
                    Toast.makeText(applicationContext, "Preencha a categoria", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}