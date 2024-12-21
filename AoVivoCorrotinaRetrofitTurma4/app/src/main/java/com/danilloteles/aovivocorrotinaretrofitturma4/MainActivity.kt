package com.danilloteles.aovivocorrotinaretrofitturma4

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.danilloteles.aovivocorrotinaretrofitturma4.api.RetrofitCustom
import com.danilloteles.aovivocorrotinaretrofitturma4.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    val jsonPlaceAPI by lazy {
        RetrofitCustom().recuperarJsonPlaceAPI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        binding.textResultado.text = "carregando"

        CoroutineScope( Dispatchers.IO ).launch {
            val resposta = jsonPlaceAPI.recuperarPostagens()
            if (resposta.isSuccessful && resposta.body() != null) {

                val listaPostagem = resposta.body()
                var textoExibicao = ""
                listaPostagem?.forEach { postagem ->
                    textoExibicao += " ${postagem.id}) ${postagem.title} \n"
                }
                withContext( Dispatchers.Main ){
                    binding.textResultado.text = textoExibicao
                }

            }else{
                withContext( Dispatchers.Main ){
                    binding.textResultado.text = "Erro ao carregar dados"
                }
            }
        }

    }
}