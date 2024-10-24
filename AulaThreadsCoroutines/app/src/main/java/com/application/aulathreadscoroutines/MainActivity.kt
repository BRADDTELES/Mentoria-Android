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
import com.application.aulathreadscoroutines.api.PostagemAPI
import com.application.aulathreadscoroutines.api.RetrofitHelper
import com.application.aulathreadscoroutines.databinding.ActivityMainBinding
import com.application.aulathreadscoroutines.model.Comentario
import com.application.aulathreadscoroutines.model.Endereco
import com.application.aulathreadscoroutines.model.Foto
import com.application.aulathreadscoroutines.model.Postagem
import com.squareup.picasso.Picasso
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
import retrofit2.Response
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

    private val apiViaCEP by lazy{
        RetrofitHelper.apiViaCEP
    }

    private val filmeAPI by lazy{
        RetrofitHelper.filmeAPI
    }

    private var pararThread = false
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
                //recuperarEndereco()
                //recuperarPostagens()
                //recuperarPostagemUnica()
                //recuperarComentariosParaPostagem()
                //salvarPostagem()
                //atualizarPostagem()
                //removerPostagem()
                recuperarFotoUnica()
            }

        }

    }

    private suspend fun recuperarFotoUnica() {

        var retorno: Response<Foto>? = null

        try {
            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            retorno = postagemAPI.recuperarFoto( 5)
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if ( retorno != null ){

            if ( retorno.isSuccessful ){
                val foto = retorno.body()

                val resultado = "[${retorno.code()}] - ${foto?.id} - ${foto?.url}"

                withContext(Dispatchers.Main){
                    binding.textResultado.text = resultado
                    Picasso.get()
                        //.load( foto?.url )
                        .load( R.drawable.picasso )
                        .resize(100, 100)
                        //.centerInside()
                        //.centerCrop()
                        .placeholder( R.drawable.carregando )
                        //.error( R.drawable.picasso )
                        .into( binding.imageFoto )
                }

                Log.i("info_jsonplace", resultado)

            }else{
                withContext(Dispatchers.Main){
                    binding.textResultado.text = "ERRO CODE:${retorno.code()}"
                    Log.i("info_jsonplace", "erro code: ${retorno.code()}")
                }
            }
        }
    }

    private suspend fun removerPostagem() {

        var retorno: Response< Unit >? = null

        try {
            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            retorno = postagemAPI.removerPostagem(1)

            /*retorno = postagemAPI.atualizarPostagemPut(
                1,
                Postagem(
                    "Corpo da postagem atualizado",
                    -1,
                    null,
                    1090
                )
            )*/
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if ( retorno != null ){
            if ( retorno.isSuccessful ){

                var resultado = "[${retorno.code()}] sucesso ao remover postagem"

                withContext(Dispatchers.Main){
                    binding.textResultado.text = resultado
                    Log.i("info_jsonplace", resultado)
                }

            }else{
                withContext(Dispatchers.Main){
                    binding.textResultado.text = "ERRO CODE:${retorno.code()}"
                    Log.i("info_jsonplace", "erro code: ${retorno.code()}")
                }
            }
        }
    }

    private suspend fun atualizarPostagem() {

        var retorno: Response< Postagem >? = null

        try {
            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            retorno = postagemAPI.atualizarPostagemPatch(
                1,
                Postagem(
                    "Corpo da postagem",
                    -1,
                    null,
                    1090
                )
            )
            /*retorno = postagemAPI.atualizarPostagemPut(
                1,
                Postagem(
                    "Corpo da postagem atualizado",
                    -1,
                    null,
                    1090
                )
            )*/
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if ( retorno != null ){
            if ( retorno.isSuccessful ){
                val postagem = retorno.body()

                val id = postagem?.id
                val titulo = postagem?.title
                val idUsuario = postagem?.userId
                val corpo = postagem?.body

                var resultado = "[${retorno.code()}] ID: $id - T: $titulo C: $corpo - U:$idUsuario"

                withContext(Dispatchers.Main){
                    binding.textResultado.text = resultado
                    Log.i("info_jsonplace", resultado)
                }

            }else{
                withContext(Dispatchers.Main){
                    binding.textResultado.text = "ERRO CODE:${retorno.code()}"
                    Log.i("info_jsonplace", "erro code: ${retorno.code()}")
                }
            }
        }
    }

    private suspend fun salvarPostagem() {

        var retorno: Response< Postagem >? = null

        val postagem = Postagem(
            "Corpo da postagem",
            -1,
            "Titulo da postagem",
            1090
        )

        try {
            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            //retorno = postagemAPI.salvarPostagens(postagem)
            retorno = postagemAPI.salvarPostagemFormulario(
                1090,
                -1,
                "Titulo da postagem Formulario",
                "Corpo da postagem Formulario"
            )
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if ( retorno != null ){
            if ( retorno.isSuccessful ){
                val postagem = retorno.body()

                val id = postagem?.id
                val titulo = postagem?.title
                val idUsuario = postagem?.userId
                var resultado = "[${retorno.code()}] id: $id - T: $titulo - U:$idUsuario"

                withContext(Dispatchers.Main){
                    binding.textResultado.text = resultado
                    Log.i("info_jsonplace", resultado)
                }

            }else{
                withContext(Dispatchers.Main){
                    binding.textResultado.text = "ERRO CODE:${retorno.code()}"
                    Log.i("info_jsonplace", "erro code: ${retorno.code()}")
                }
            }
        }
    }

    private suspend fun recuperarComentariosParaPostagem() {

        /*var retorno: Response< List<Comentario> >? = null

        try {
            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            //retorno = postagemAPI.recuperarComentariosParaPostagem(1) //Path
            retorno = postagemAPI.recuperarComentariosParaPostagemQuery(1) //Query
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if ( retorno != null ){

            if ( retorno.isSuccessful ){
                val listaComentarios = retorno.body()

                var resultado = ""

                listaComentarios?.forEach {  comentario ->
                    val idComentario = comentario.id
                    val email = comentario.email
                    val comentarioResultado = "$idComentario - $email \n"
                    resultado += comentarioResultado
                }

                withContext(Dispatchers.Main){
                    binding.textResultado.text = resultado
                    Log.i("info_jsonplace", resultado)
                }

            }
        }*/
    }

    private suspend fun recuperarPostagemUnica() {

        /*var retorno: Response<Postagem>? = null

        try {
            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            retorno = postagemAPI.recuperarPostagemUnica( 2)
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if ( retorno != null ){

            if ( retorno.isSuccessful ){
                val postagem = retorno.body()
                val resultado = "${postagem?.id} - ${postagem?.title}"

                withContext(Dispatchers.Main){
                    binding.textResultado.text = resultado // Mostrar a Postagem na tela do usuário
                }

                Log.i("info_jsonplace", resultado)

            }
        }*/
    }

    private suspend fun recuperarPostagens() {

        var retorno: Response< List<Postagem> >? = null

        try {
            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            retorno = postagemAPI.recuperarPostagens()
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_jsonplace", "erro ao recuperar")
        }

        if ( retorno != null ){

            if ( retorno.isSuccessful ){
                val listaPostagens = retorno.body()
                listaPostagens?.forEach {  postagem ->
                    val id = postagem.id
                    val title = postagem.title
                    Log.i("info_jsonplace", "$id - $title")
                }

            }
        }
    }

    private suspend fun recuperarEndereco(){

        var retorno: Response<Endereco>? = null
        val cepDigitadoUsuario = "74375150"
        try {
            val enderecoAPI = retrofit.create( EnderecoAPI::class.java )
            retorno = enderecoAPI.recuperarEndereco( cepDigitadoUsuario )
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_endereco", "erro ao recuperar")
        }

        if ( retorno != null ){

            if ( retorno.isSuccessful ){
                val endereco = retorno.body()
                val rua = endereco?.logradouro
                val cidade = endereco?.localidade
                val cep = endereco?.teste
                Log.i("info_endereco", "endereço: $rua, $cidade t: $cep")

            }
        }

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