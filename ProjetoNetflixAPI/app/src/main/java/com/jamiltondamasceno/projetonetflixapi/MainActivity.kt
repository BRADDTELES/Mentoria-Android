package com.jamiltondamasceno.projetonetflixapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.jamiltondamasceno.projetonetflixapi.adapter.FilmeAdapter
import com.jamiltondamasceno.projetonetflixapi.api.RetrofitService
import com.jamiltondamasceno.projetonetflixapi.databinding.ActivityMainBinding
import com.jamiltondamasceno.projetonetflixapi.model.Endereco
import com.jamiltondamasceno.projetonetflixapi.model.FilmeRecente
import com.jamiltondamasceno.projetonetflixapi.model.FilmeResposta
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var paginaAtual = 1
    private val TAG = "info_filme"
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    private val filmeAPI by lazy {
        RetrofitService.filmeAPI // Instanciando a propriedade filmeAPI trazendo a configuração da RetrofitService
    }
    private val viaCepAPI by lazy {
        RetrofitService.recuperarViaCep() // Instanciando o metodo recuperarViaCep trazendo a configuração da RetrofitService
    }
    var jobFilmeRecente: Job? = null // Variável para armazenar a Job da Coroutine
    var jobFilmesPopulares: Job? = null // Variável para armazenar a Job da Coroutine
    var linearLayoutManager: LinearLayoutManager? = null
    var gridLayoutManager: GridLayoutManager? = null
    private lateinit var filmeAdapter: FilmeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        inicializarViews()
        recuperarEndereco()

    }

    private fun recuperarEndereco() {

        CoroutineScope(Dispatchers.IO).launch {

            var resposta: Response<Endereco>? = null

            // Fazendo a requisição
            try {
                resposta = viaCepAPI.recuperarEndereco()
            }catch (e: Exception){
                exibirMensagem("Erro ao fazer a requisição")
            }

            if (resposta != null){
                if (resposta.isSuccessful){

                    val endereco = resposta.body()
                    if ( endereco != null ){
                        val logradouro = endereco.logradouro
                        val bairro = endereco.bairro
                        val complemento = endereco.complemento
                        val localidade = endereco.localidade
                        val estado = endereco.estado
                        val uf = endereco.uf

                        Log.i("viacep", "recuperarEndereco: $logradouro - $bairro - $complemento - $localidade - $estado - $uf")
                    }

                }else{
                    exibirMensagem("Não foi possivel recuperar o endereco CODIGO: ${resposta.code()}")
                }
            }else{
                exibirMensagem("Não foi possível fazer a requisição")
            }
        }
    }

    private fun inicializarViews() {

        filmeAdapter = FilmeAdapter{ filme ->
            val intent = Intent(this, DetalhesActivity::class.java)
            intent.putExtra("filme", filme)
            startActivity(intent)
        }
        binding.rvPopulares.adapter = filmeAdapter

        /*// Realizar listagem dos filmes um do lado do outro
        linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvPopulares.layoutManager = linearLayoutManager*/

        // Realizar listagem dos filmes em grade de 2 colunas (Grid)
        gridLayoutManager = GridLayoutManager(
            this,
            2 // Quantidade de colunas
        )
        binding.rvPopulares.layoutManager = gridLayoutManager

        binding.rvPopulares.addOnScrollListener( object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val podeDescerVerticalmente = recyclerView.canScrollVertically(1)// Verificar scroll na vertical e se está descendo

                if ( !podeDescerVerticalmente ){//Não NÃO poder descer
                    //Carregar mais 20 itens
                    Log.i("recycler_api", "poginaAtual: $paginaAtual")
                    recuperarFilmesPopularesProximaPagina()
                }else{

                }

                //Log.i("recycler_api", "podeDescerVerticalmente: $podeDescerVerticalmente")

                /*// Logica Verificar se chegou no final da lista e esconder a seta de adicionar ao final da lista (VERTICAL/HORIZONTAL)
                val ultimoItemVisivel = linearLayoutManager?.findLastVisibleItemPosition()
                val totalItens = recyclerView.adapter?.itemCount
                //Log.i("recycler_test", "ultimo: $ultimoItemVisivel total: $totalItens")
                if ( ultimoItemVisivel != null && totalItens != null ){
                    if ( totalItens-1 == ultimoItemVisivel ){//Chegou no uĺtimo item
                        binding.fabAdicionar.hide()
                    }else{//Não chegou no último item
                        binding.fabAdicionar.show()
                    }
                }*/

                /*// Logica Esconder a seta de adicionar quando o scroll desce e quando sobe aparece a seta (VERTICAL)
                Log.i("recycler_test", "onScrolled: dx: $dx, dy: $dy")
                if (dy > 0) {//descendo
                    binding.fabAdicionar.hide()
                }else{//dy < 0 está subindo
                    binding.fabAdicionar.show()
                }*/
            }
        })


    }

    override fun onStart() {
        super.onStart()
        recuperarFilmeRecente()
        recuperarFilmesPopulares()
    }

    private fun recuperarFilmesPopularesProximaPagina(){

        if ( paginaAtual < 1000 ){
            paginaAtual++
            recuperarFilmesPopulares(paginaAtual)
        }
    }

    // Método para recuperar os filmes populares
    private fun recuperarFilmesPopulares(pagina: Int = 1) {
        // Essa Coroutine será parada, caso o usuário saia desta tela
        jobFilmesPopulares = CoroutineScope(Dispatchers.IO).launch {
            var resposta: Response<FilmeResposta>? = null

            // Fazendo a requisição
            try {
                resposta = filmeAPI.recuperarFilmesPopulares(pagina)
            }catch (e: Exception){
                exibirMensagem("Erro ao fazer a requisição")
            }

            if (resposta != null){
                if (resposta.isSuccessful){

                    val filmeResposta = resposta.body()// Capturar o conteudo
                    val listaFilmes = filmeResposta?.filmes// Capturar a lista de filmes vinda de filmeResposta
                    // Testar se a lista de filmes não está vazia e se não for nula
                    if ( listaFilmes != null && listaFilmes.isNotEmpty()){

                        // Trocar o fluxo da Coroutine para o Main Thread
                        withContext(Dispatchers.Main){
                            filmeAdapter.adicionarLista( listaFilmes ) // Carregar a lista de filmes
                        }

                        // Percorrer a lista de filmes e exibir o titulo de cada um no Logcat
                        /*listaFilmes.forEach{ filme ->
                            Log.i("filmes_api", "Titulo: ${filme.title}")
                        }*/

                    }

                }else{
                    exibirMensagem("Não foi possivel recuperar o filme recente CODIGO: ${resposta.code()}")
                }
            }else{
                exibirMensagem("Não foi possível fazer a requisição")
            }
        }
    }

    // Método para recuperar o filme mais recente como fundo de tela de capa
    private fun recuperarFilmeRecente() {
        // Essa Coroutine será parada, caso o usuário saia desta tela
        jobFilmeRecente = CoroutineScope(Dispatchers.IO).launch {
            var resposta: Response<FilmeRecente>? = null

            // Fazendo a requisição
            try {
                resposta = filmeAPI.recuperarFilmeRecente()
            }catch (e: Exception){
                exibirMensagem("Erro ao fazer a requisição")
            }

            if (resposta != null){
                if (resposta.isSuccessful){
                    // Capturar o conteudo
                    val filmeRecente = resposta.body()
                    val nomeImagem = filmeRecente?.poster_path // Caminho da imagem
                    val titulo = filmeRecente?.title
                    val url = RetrofitService.BASE_URL_IMAGEM + "w780" + nomeImagem // URL da imagem

                    withContext( Dispatchers.Main ){
                        /*// Mostrar o titulo e a imagem no Logcat
                        val texto = "titulo: $titulo url: $url"
                        Log.i("filmes_api", texto)*/
                        // Exibir a imagem de filme Recente no fundo da tela usando Picasso (Precisa do withContext(Dispatchers.Main) para funcionar)
                        Picasso.get()
                            .load(url)
                            .error(R.drawable.capa)// Caso a imagem não seja carregada, exibir essa imagem de capa Padrão para não quebrar a tela
                            .into(binding.imgCapa)
                    }
                }else{
                    exibirMensagem("Não foi possivel recuperar o filme recente CODIGO: ${resposta.code()}")
                }
            }else{
                exibirMensagem("Não foi possível fazer a requisição")
            }
        }
    }

    private fun exibirMensagem(mensagem: String) {
        Toast.makeText(
            applicationContext,
            mensagem,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onStop() {
        super.onStop()
        jobFilmeRecente?.cancel() // Cancelar a Coroutine quando a Activity for parada
        jobFilmesPopulares?.cancel() // Cancelar a Coroutine quando a Activity for parada
    }

}