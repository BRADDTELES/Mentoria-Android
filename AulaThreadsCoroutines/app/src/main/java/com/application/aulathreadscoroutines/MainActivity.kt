package com.application.aulathreadscoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.application.aulathreadscoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Thread.currentThread
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var pararThread = false

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
        }

        binding.btnParar.setOnClickListener {
            pararThread = true
            binding.btnIniciar.text = "Reiniciar execução"
            binding.btnIniciar.isEnabled = true
        }

        binding.btnIniciar.setOnClickListener {

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

            CoroutineScope( Dispatchers.IO ).launch {
                repeat(15){ indice ->
                    Log.i("info_coroutine", "Executando: $indice T: ${currentThread().name}")

                    withContext( Dispatchers.Main ){
                        binding.btnIniciar.text = "Executando: $indice T: ${currentThread().name}"
                    }

                    delay(1000)//ms 1000 -> 1s
                }
            }

        }

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