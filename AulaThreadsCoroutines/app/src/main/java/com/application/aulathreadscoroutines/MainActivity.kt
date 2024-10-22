package com.application.aulathreadscoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.application.aulathreadscoroutines.api.EnderecoAPI
import com.application.aulathreadscoroutines.api.RetrofitHelper
import com.application.aulathreadscoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.lang.Thread.currentThread
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val retrofit by lazy{
        RetrofitHelper.retrofit
    }

    private var pararThread = false
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAbrir.setOnClickListener {
            startActivity(
                Intent(this, SegundaActivity::class.java)
            )
            finish()
        }

        binding.btnParar.setOnClickListener {
            //pararThread = true
            job?.cancel()
            binding.btnIniciar.text = "Reiniciar execução"
            binding.btnIniciar.isEnabled = true
        }

        binding.btnIniciar.setOnClickListener {

        /*
            //CoroutineScope(Dispatchers.Main).launch {
            //MainScope().launch {
            //CoroutineScope(Dispatchers.IO).launch {
            //GlobalScope.launch {
            //lifecycleScope.launch {
            /*runBlocking {
                binding.btnIniciar.text = "Executando"
                *//*repeat(30){ indice ->
                    //binding.btnIniciar.text = "Executando $indice"
                    Log.i("info_coroutine", "Executando: $indice T: ${Thread.currentThread().name}")
                    delay(1000L)
                }*//*
            }*/

            /*repeat(15){ indice ->
                Log.i("info_thread", "Executando: $indice T: ${Thread.currentThread().name}")
                Thread.sleep(1000)//ms 1000 -> 1s
            }*/
            //MinhaThread().start()
            //Thread( MinhaRunnable() ).start()
            /*Thread{
                repeat(30){ indice ->
                    Log.i("info_thread", "MinhaThread: $indice T: ${Thread.currentThread().name}")
                    runOnUiThread {
                        binding.btnIniciar.text = "Executando: $indice T: ${currentThread().name}"
                        binding.btnIniciar.isEnabled = false
                        if ( indice == 29 ){
                            binding.btnIniciar.text = "Reiniciar execução"
                            binding.btnIniciar.isEnabled = true
                        }
                    }
                    Thread.sleep(1000)//ms 1000 -> 1s
                }
            }.start()*/

            /*job = CoroutineScope( Dispatchers.IO ).launch {
                *//*repeat(15){ indice ->
                    Log.i("info_coroutine", "Executando: $indice T: ${currentThread().name}")

                    withContext( Dispatchers.Main ){
                        binding.btnIniciar.text = "Executando: $indice T: ${currentThread().name}"
                    }

                    delay(1000)//ms 1000 -> 1s
                }*//*

                //executar()//só funciona dentro de uma Coroutine
                *//*withTimeout(7000L){
                    executar()
                }*//*

                val tempo = measureTimeMillis {

                    *//*var resultado1: String? = null
                    var resultado2: String? = null

                    val job1 = launch {
                        resultado1 = tarefa1()//Pedro
                    }

                    val job2 = launch {
                        resultado2 = tarefa2()//Maria
                    }

                    job1.join()
                    job2.join()

                    Log.i("info_coroutine", "resultado1: ${resultado1}")
                    Log.i("info_coroutine", "resultado2: ${resultado2}")*//*

                    val resultado1 = async {tarefa1()}//Pedro
                    val resultado2 = async {tarefa2()}//Maria

                    withContext( Dispatchers.Main ){
                        binding.btnIniciar.text = "${resultado1.await()}"
                        binding.btnParar.text = "${resultado2.await()}"
                    }

                    Log.i("info_coroutine", "resultado1: ${resultado1.await()}")
                    Log.i("info_coroutine", "resultado2: ${resultado2.await()}")

                }
                Log.i("info_coroutine", "Tempo de execução: $tempo")
            }*/
*/

            CoroutineScope(Dispatchers.IO).launch {
                recuperarEndereco()
            }

        }

    }

    private suspend fun recuperarEndereco(){

        val enderecoAPI = retrofit.create( EnderecoAPI::class.java )
        enderecoAPI.recuperarEndereco()

    }

    private suspend fun tarefa1() : String {
        repeat(3){ indice ->
            Log.i("info_coroutine", "tarefa1: $indice T: ${currentThread().name}")
            delay(1000)//ms 1000 -> 1s
        }
        return "Executou tarefa 1"
    }

    private suspend fun tarefa2() : String {
        repeat(5){ indice ->
            Log.i("info_coroutine", "tarefa2: $indice T: ${currentThread().name}")
            delay(1000)//ms 1000 -> 1s
        }
        return "Executou tarefa 2"
    }

    private suspend fun executar(){
        repeat(15){ indice ->
            Log.i("info_coroutine", "Executando: $indice T: ${currentThread().name}")

            withContext( Dispatchers.Main ){
                binding.btnIniciar.text = "Executando: $indice T: ${currentThread().name}"
                binding.btnIniciar.isEnabled = false
            }

            delay(1000L)//ms 1000 -> 1s
        }
    }

    private suspend fun dadosUsuaio(){
        val usuario = recuperarUsuarioLogado()
        Log.i("info_coroutine", "usuário: ${usuario.nome} T: ${currentThread().name}")
        val postagens = recuperarPostagensPeloId( usuario.id )
        Log.i("info_coroutine", "postagens: ${postagens.size} T: ${currentThread().name}")
    }

    private suspend fun recuperarPostagensPeloId( idUsuario: Int ) : List<String>{
        delay(2000)//2s
        return listOf(
            "Viagem Nordeste",
            "Estudando Android",
            "Jantando restaurante"
        )
    }

    private suspend fun recuperarUsuarioLogado(): Usuario{

        delay(2000)//2s
        return Usuario(1020, "Jamilton Damasceno")

    }

    inner class MinhaRunnable : Runnable{
        override fun run() {
            repeat(30){ indice ->

                if (pararThread){
                    pararThread = false
                    return
                }

                Log.i("info_thread", "MinhaThread: $indice T: ${Thread.currentThread().name}")
                runOnUiThread {
                    binding.btnIniciar.text = "Executando: $indice T: ${currentThread().name}"
                    binding.btnIniciar.isEnabled = false
                    if ( indice == 29 ){
                        binding.btnIniciar.text = "Reiniciar execução"
                        binding.btnIniciar.isEnabled = true
                    }
                }
                Thread.sleep(1000)//ms 1000 -> 1s
            }
        }

    }

    inner class MinhaThread : Thread(){

        override fun run() {
            super.run()

            repeat(30){ indice ->
                Log.i("info_thread", "MinhaThread: $indice T: ${currentThread().name}")
                runOnUiThread {
                    binding.btnIniciar.text = "Executando: $indice T: ${currentThread().name}"
                    binding.btnIniciar.isEnabled = false
                    if ( indice == 29 ){
                        binding.btnIniciar.text = "Reiniciar execução"
                        binding.btnIniciar.isEnabled = true
                    }
                }
                sleep(1000)//ms 1000 -> 1s
            }

        }
    }

}