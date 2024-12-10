package com.application.aulaserviceandroid

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class MeuServico : Service() {
   
   private val coroutine = CoroutineScope( Dispatchers.IO )

   override fun onBind(intent: Intent?): IBinder? {
      return null
   }

   override fun onCreate() {
      super.onCreate()
      Log.i("servico_android", "onCreate")
   }

   override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
      //CÓDIGOS DO SERVIÇO
      /*
      Thread para execução
      val minhaThread = MinhaThread()
      minhaThread.run()*/
      val bundle = intent?.extras
      val tempoDuracao = bundle?.getLong("tempoDuracao")
      val tempo = tempoDuracao ?: 2000L

      coroutine.launch {
         repeat(15){ contador ->
            //sleep(2000)
            delay(tempo)
            Log.i("servico_android", "executando: $contador tempo: $tempo")
         }
         stopSelf()//Parar o serviço
      }

      return super.onStartCommand(intent, flags, startId)
   }

   inner class MinhaThread : Thread() {
      override fun run() {
         super.run()
         repeat(15){ contador ->
            sleep(2000)
            Log.i("servico_android", "executando serviço: $contador")
         }
         stopSelf()//Parar o serviço
      }
   }

   override fun onDestroy() {//DESTRUI O SERVIÇO
      Log.i("servico_android", "onDestroy")
      coroutine.cancel()
      super.onDestroy()
   }

}