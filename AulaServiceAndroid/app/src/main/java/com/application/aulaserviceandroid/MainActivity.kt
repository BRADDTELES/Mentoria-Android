package com.application.aulaserviceandroid

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.application.aulaserviceandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ServiceConnection {

   private val binding by lazy {
       ActivityMainBinding.inflate( layoutInflater )
   }
   private lateinit var serviceConnection: ServiceConnection
   private lateinit var servicoConectado: MinhaConexao
   /*private var serviceConnection: ServiceConnection = object : ServiceConnection {
      override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {

      }

      override fun onServiceDisconnected(p0: ComponentName?) {

      }

   }*/


   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(binding.root)

      solicitarPermissaoNotificacao()

      serviceConnection = this
      //val meuServico = Intent(this, MeuServico::class.java)
      val minhaConexaoServico = Intent(this, MinhaConexao::class.java)
      binding.btnIniciarService.setOnClickListener {
         /*meuServico.putExtra("tempoDuracao",3000L)
         startService( meuServico )*/
         //minhaConexaoServico.putExtra("tempoDuracao",3000L)

         // >= 26 - startForegroundService
         // < 26 - startService
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(minhaConexaoServico)
         }else{
            startService( minhaConexaoServico )//Inicia um serviço
         }


         bindService(
            minhaConexaoServico,
            serviceConnection,
            BIND_AUTO_CREATE
         )//conecta em um serviço
      }

      binding.btnPararService.setOnClickListener {
         /*stopService( meuServico )*/
         stopService( minhaConexaoServico )
         unbindService( serviceConnection )//desconecta o serviço
      }

      binding.btnPegarDados.setOnClickListener {
         val contador = servicoConectado.contador
         Toast.makeText(
            this,
            "Contador: $contador",
            Toast.LENGTH_SHORT
         ).show()
      }

   }

   private fun solicitarPermissaoNotificacao() {

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
         val permissaoNotificacao = ActivityCompat.checkSelfPermission(
            this, android.Manifest.permission.POST_NOTIFICATIONS
         )
         if (permissaoNotificacao == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
               this,
               arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
               100
            )
         }
      }

   }

   override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
      Log.i("servico_android", "onServiceConnected")
      val customBinder = service as MinhaConexao.CustomBinder
      servicoConectado = customBinder.recuperarServico()
   }

   override fun onServiceDisconnected(name: ComponentName?) {
      Toast.makeText(this, "Serviço desconectado", Toast.LENGTH_SHORT).show()
   }
}