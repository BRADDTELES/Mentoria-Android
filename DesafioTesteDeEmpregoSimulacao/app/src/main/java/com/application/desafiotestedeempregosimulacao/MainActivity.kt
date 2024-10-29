package com.application.desafiotestedeempregosimulacao

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.desafiotestedeempregosimulacao.api.RetrofitCustom
import com.application.desafiotestedeempregosimulacao.databinding.ActivityMainBinding
import com.application.desafiotestedeempregosimulacao.model.Resultado
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var galeriaAdapter: GaleriaAdapter // Adaptador para a RecyclerView para carregar/atualizar dados da Web - galeria de imagens
    private val imgurAPI by lazy {
        RetrofitCustom.imgurAPI
    }
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        galeriaAdapter = GaleriaAdapter()
        /*galeriaAdapter.adicionarLista(
            listOf(
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000"
            )
        )*/

        binding.rvGaleria.adapter = galeriaAdapter // galeriaAdapter é do tipo adapter
        binding.rvGaleria.layoutManager = GridLayoutManager(
            this, // contexto
            3, // quantidade de colunas
            RecyclerView.VERTICAL, // orientação da lista (vertical)
            false // reverseLayout (false)
        )

    }

    override fun onStart() {
        super.onStart()
        recuperarImagensAPI()
    }

    fun recuperarImagensAPI(){
        job = CoroutineScope(Dispatchers.IO).launch {

            var response: Response<Resultado>? = null

            try {
                response =  imgurAPI.pesquisarImagensGaleria("cats")
            }catch (e: Exception) {
                e.printStackTrace()
            }

            if (response != null && response.isSuccessful) {

                val resultado = response.body()
                if (resultado != null){
                    val lista = resultado.data

                    val listaUrlImagens = mutableListOf<String>()
                    lista.forEach{ dados ->
                        val imagem = dados.images[0]
                        val tipo = imagem.type
                        if ( tipo == "image/jpeg" || tipo == "image/png" ){
                            listaUrlImagens.add( imagem.link )
                        }
                    }
                    withContext(Dispatchers.Main){
                        galeriaAdapter.adicionarLista(listaUrlImagens)
                    }
                }

            }else{
                Log.i("teste_galeria", "Erro ao recuperar imagem")
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()

    }
}