package com.jamiltondamasceno.projetonetflixapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.jamiltondamasceno.projetonetflixapi.api.RetrofitService
import com.jamiltondamasceno.projetonetflixapi.databinding.ActivityMainBinding
import com.jamiltondamasceno.projetonetflixapi.model.FilmeRecente
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
    var jobFilmeRecente: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )



    }



    override fun onStart() {
        super.onStart()
        recuperarFilmeRecente()
    }

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
                        Log.i("info_netflix", texto)*/
                        // Exibir a imagem pela Picasso (Mas precisa do withContext(Dispatchers.Main) para funcionar)
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
        jobFilmeRecente?.cancel()
    }

}