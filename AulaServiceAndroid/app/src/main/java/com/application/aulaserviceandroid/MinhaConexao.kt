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

class MinhaConexao : Service() {

   private val coroutine = CoroutineScope( Dispatchers.IO )

   override fun onBind(intent: Intent): IBinder? {
      return null
   }

   override fun onCreate() {
      super.onCreate()
      Log.i("servico_android", "onCreate")
   }

   override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
      Log.i("servico_android", "onStartCommand")

      coroutine.launch {
         repeat(20){ contador ->
            //sleep(2000)
            delay(2000)
            Log.i("servico_android", "executando: $contador")
         }
         stopSelf()//Parar o serviço
      }

      return super.onStartCommand(intent, flags, startId)
      //return START_NOT_STICKY
      //return START_REDELIVER_INTENT
   }

   override fun onDestroy() {
      Log.i("servico_android", "onDestroy")
      coroutine.cancel()
      super.onDestroy()
   }
}