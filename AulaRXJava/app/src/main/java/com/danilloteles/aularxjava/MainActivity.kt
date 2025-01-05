package com.danilloteles.aularxjava

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aularxjava.api.Postagem
import com.danilloteles.aularxjava.api.RetrofitCustom
import com.danilloteles.aularxjava.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

  private val binding by lazy {
    ActivityMainBinding.inflate( layoutInflater )
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView( binding.root )

    //criarObersavadorObservavel()
    binding.btnExecutar.setOnClickListener {
      listarDadosApi()
    }

  }

  private fun listarDadosApi() {

    val jsonPlaceApi = RetrofitCustom.jsonPlace

    //Observador -> Activity
    val observador = object : Observer<List<Postagem>> {

      override fun onNext(listaPostagens: List<Postagem>) {
        var textoResultado = ""
        listaPostagens.forEach {  postagem ->
          textoResultado += " ${postagem.id}) ${postagem.title}  \n"
        }
        binding.textResultado.text = textoResultado
        Log.i("info_rxjava", textoResultado)
      }

      override fun onSubscribe(d: Disposable) {}
      override fun onError(e: Throwable) {}
      override fun onComplete() {}



    }

    //Observável -> ViewModel
    val observavel = jsonPlaceApi.recuperarPostagens()

    //Activity
    observavel
      .subscribeOn( Schedulers.io() )//Carregando dados -> Executado observável
      .observeOn( AndroidSchedulers.mainThread() )//Exibição -> Observando em
      .subscribe( observador )

  }

  private fun criarObersavadorObservavel() {

    //Observável - ViewModel
    /*val listaNomes = listOf("Jamilton", "Ana", "Maria")//API, Banco dados
    val obersavel = Observable.fromIterable( listaNomes )*///maneira estática
    val obersavel = Observable.create<String> { emissor -> //maneira dinâmica
      emissor.onNext("Jamilton")
      emissor.onNext("Ana")
      emissor.onNext("Marcos")
      emissor.onComplete()
    }

    //Observador - Activity
    val observador = object : Observer<String> {
      override fun onSubscribe(d: Disposable) {
        Log.i("info_rxjava","onSubscribe: inicio execução")
      }

      override fun onNext(item: String) {
        Log.i("info_rxjava","onNext - Nome: $item")
      }

      override fun onError(e: Throwable) {
        Log.i("info_rxjava","onError: ${e.message}")
      }

      override fun onComplete() {
        Log.i("info_rxjava","onComplete: fim execução")
      }

    }
    
    obersavel.subscribe( observador )

  }
}