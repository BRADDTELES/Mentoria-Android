package com.application.aulaserviceandroid

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MeuServico : Service() {

   override fun onBind(intent: Intent?): IBinder? {
      return null
   }

   override fun onCreate() {
      super.onCreate()
      Log.i("servico_android", "onCreate")
   }

   override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
      //CÓDIGOS DO SERVIÇO
      val minhaThread = MinhaThread()
      minhaThread.run()
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
      super.onDestroy()
   }

}