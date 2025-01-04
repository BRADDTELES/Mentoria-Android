package com.danilloteles.aularoommvvmpratica.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.aularoommvvmpratica.data.entity.Anotacao
import com.danilloteles.aularoommvvmpratica.databinding.ActivityCadastroAnotacaoBinding
import com.danilloteles.aularoommvvmpratica.presentation.viewmodel.AnotacaoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroAnotacaoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroAnotacaoBinding.inflate( layoutInflater )
    }
    private val anotacaoViewModel: AnotacaoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        inicializarListeners()
        inicializarObservables()

    }

    private fun inicializarObservables() {
        anotacaoViewModel.resultadoOperacao.observe(this){ resultado ->
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

            btnSalvarAnotacao.setOnClickListener {
                val titulo = editTituloAnotacao.text.toString()
                val descricao = editDescricaoAnotacao.text.toString()
                //val idCategoria = spinnerCategorias.id.toLong()
                val idCategoria = 1L
                val anotacao = Anotacao(
                    0, idCategoria, titulo, descricao
                )
                anotacaoViewModel.salvar( anotacao )
            }

            btnAdicionarCategoria.setOnClickListener {
                startActivity(
                    Intent(applicationContext, CadastroCategoriaActivity::class.java)
                )
            }
        }
    }
}