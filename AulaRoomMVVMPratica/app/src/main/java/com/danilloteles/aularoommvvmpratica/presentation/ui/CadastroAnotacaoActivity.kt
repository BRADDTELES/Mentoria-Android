package com.danilloteles.aularoommvvmpratica.presentation.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.aularoommvvmpratica.data.entity.Anotacao
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria
import com.danilloteles.aularoommvvmpratica.databinding.ActivityCadastroAnotacaoBinding
import com.danilloteles.aularoommvvmpratica.presentation.viewmodel.AnotacaoViewModel
import com.danilloteles.aularoommvvmpratica.presentation.viewmodel.CategoriaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroAnotacaoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroAnotacaoBinding.inflate( layoutInflater )
    }
    private val anotacaoViewModel: AnotacaoViewModel by viewModels()
    private val categoriaViewModel: CategoriaViewModel by viewModels()
    private lateinit var spinnerAdapter: ArrayAdapter<String>
    private lateinit var listaCategorias: List<Categoria>
    private var anotacao: Anotacao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
        inicializarUI()
        inicializarListeners()
        inicializarObservables()

    }

    override fun onStart() {
        super.onStart()
        categoriaViewModel.listar()
    }

    private fun inicializarUI() {

        with( binding ){

            val bundle = intent.extras
            if (bundle != null) {

                anotacao = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    bundle.getParcelable("anotacao", Anotacao::class.java)
                }else{
                    bundle.getParcelable("anotacao")
                }

                if (anotacao != null) {
                    binding.editTituloAnotacao.setText( anotacao!!.titulo )
                    binding.editDescricaoAnotacao.setText( anotacao!!.descricao )
                }

            }

            spinnerAdapter = ArrayAdapter(
                applicationContext,
                android.R.layout.simple_spinner_dropdown_item,
                mutableListOf()
            )
            spinnerCategorias.adapter = spinnerAdapter
            
        }

    }

    private fun inicializarObservables() {

        categoriaViewModel
            .listaCategorias
            .observe(this){ listaCategoriasRecuperadas ->
                listaCategorias = listaCategoriasRecuperadas

                /*
                0) Selecione uma categoria
                1) Mercado
                2) Filmes
                * */
                val listaSpinner = mutableListOf("Selecione uma categoria")
                val listaTitulosCategorias = listaCategoriasRecuperadas.map { categoria ->
                    categoria.nome
                }
                listaSpinner.addAll( listaTitulosCategorias )
                spinnerAdapter.clear()
                spinnerAdapter.addAll( listaSpinner )

                var posicaoAnotacaoSelecionada = 0
                if (anotacao != null) {
                    val idCategoriaAnotacaoEdicao = anotacao!!.idCategoria
                    var posicaoAtual = 0
                    listaCategorias.forEach { categoria ->
                        if ( idCategoriaAnotacaoEdicao == categoria.idCategoria ) {
                            posicaoAnotacaoSelecionada = posicaoAtual + 1
                            return@forEach
                        }
                        posicaoAtual++
                    }
                }

                binding.spinnerCategorias.setSelection( posicaoAnotacaoSelecionada )

        }

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
                var idCategoria = 0L

                //capturar categoria
                val posicaoCategoriaSelecionada = spinnerCategorias.selectedItemPosition

                if ( posicaoCategoriaSelecionada > 0 ) {
                    val categoria = listaCategorias[ posicaoCategoriaSelecionada - 1 ]
                    idCategoria = categoria.idCategoria
                }

                if ( anotacao != null) {//editando
                    val anotacao = Anotacao(
                        anotacao!!.idAnotacao, idCategoria, titulo, descricao
                    )
                    anotacaoViewModel.atualizar( anotacao )
                }else{//salvando
                    val anotacao = Anotacao(
                        0, idCategoria, titulo, descricao
                    )
                    anotacaoViewModel.salvar( anotacao )
                }
            }

            btnAdicionarCategoria.setOnClickListener {
                startActivity(
                    Intent(applicationContext, CadastroCategoriaActivity::class.java)
                )
            }
        }
    }
}