package com.jamiltondamasceno.projetonetflixapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jamiltondamasceno.projetonetflixapi.adapter.FilmeAdapter
import com.jamiltondamasceno.projetonetflixapi.api.RetrofitService
import com.jamiltondamasceno.projetonetflixapi.databinding.ActivityMainBinding
import com.jamiltondamasceno.projetonetflixapi.model.Filme
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

    private val TAG = "info_filme"
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    private val filmeAPI by lazy {
        RetrofitService.filmeAPI
    }
    var jobFilmeRecente: Job? = null // Variável para armazenar a Job da Coroutine
    var jobFilmesPopulares: Job? = null // Variável para armazenar a Job da Coroutine
    private lateinit var filmeAdapter: FilmeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        inicializarViews()

    }

    private fun inicializarViews() {

        filmeAdapter = FilmeAdapter{ filme ->
            val intent = Intent(this, DetalhesActivity::class.java)
            intent.putExtra("filme", filme)
            startActivity(intent)
        }
        binding.rvPopulares.adapter = filmeAdapter

        // Realizar listagem dos filmes um do lado do outro
        binding.rvPopulares.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

    }


    override fun onStart() {
        super.onStart()
        recuperarFilmeRecente()
        recuperarFilmesPopulares()
    }

    // Método para recuperar os filmes populares
    private fun recuperarFilmesPopulares() {
        // Essa Coroutine será parada, caso o usuário saia desta tela
        jobFilmesPopulares = CoroutineScope(Dispatchers.IO).launch {
            var resposta: Response<FilmeResposta>? = null

            // Fazendo a requisição
            try {
                resposta = filmeAPI.recuperarFilmesPopulares()
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