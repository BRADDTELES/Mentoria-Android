package com.app.aulaturma02service

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.aulaturma02service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ServiceConnection {
    
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var serviceConnection: ServiceConnection
    private lateinit var servicoRecuperado: MeuServico
    /*private val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            TODO("Not yet implemented")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        /*

        ServicoAtualizPostagens
        ServicoAtualizaLocalizacao
        ServicoLembrete

        stopService(Intent(this, ServicoAtualizPostagens::class.java))

        * */

        serviceConnection = this
        val meuServico = Intent(this, MeuServico::class.java)
        binding.btnIniciar.setOnClickListener {
            val tempoExecucao = 3000L
            meuServico.putExtra("tempoExecucao", tempoExecucao)
            startService( meuServico )
            bindService(// Criando a conexão
                meuServico,
                serviceConnection,// Inicia a conexão
                Context.BIND_AUTO_CREATE
            )
        }

        // Parando o Serviço
        binding.btnParar.setOnClickListener {
            stopService( meuServico )
            unbindService( serviceConnection )// Encerrar a conexão
        }

        binding.btnExibir.setOnClickListener {
            val contador = servicoRecuperado.contador
            val idServico = servicoRecuperado.idServico

            Toast.makeText(
                this,
                "contador: $contador idServico: $idServico",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as MeuServico.LocalBinder
        servicoRecuperado = binder.recuperarServico()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        //Android encerra a conexão
    }

}