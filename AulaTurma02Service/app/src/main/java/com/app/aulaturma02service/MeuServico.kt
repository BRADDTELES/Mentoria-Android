package com.app.aulaturma02service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Criando o Serviço - Que possui Ciclo de Vida: onCreate, onStartCommand, onDestroy
class MeuServico : Service() {

    private val coroutineScope = CoroutineScope( Dispatchers.IO )
    var idServico: Int = 0
    var contador: Int = 0

    inner class LocalBinder : Binder() {
        fun recuperarServico() : MeuServico {
            return this@MeuServico
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return LocalBinder()
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("servico_aula","onCreate: ")
    }

    // onStart está por trás dos panos do onStartCommand
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("servico_aula","onStartCommand: ")
        idServico = startId
        val bundle = intent?.extras
        val tempoRecuperado = bundle?.getLong("tempoExecucao")
        val tempoExecucao = tempoRecuperado ?: 1000L
        coroutineScope.launch {
            repeat(15){ i ->
                contador = i
                Log.i("servico_aula","executando: $i") // Executa a tarefa em background com coroutines
                delay(tempoExecucao)
            }
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.i("servico_aula","onDestroy: ")
        coroutineScope.cancel()
        super.onDestroy()
    }
}