package com.application.aulaserviceandroid

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MeuServico : Service() {

   override fun onBind(intent: Intent?): IBinder? {
      return null
   }

   override fun onCreate() {
      super.onCreate()

   }

   override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
      //CÓDIGOS DO SERVIÇO
      return super.onStartCommand(intent, flags, startId)
   }

   override fun onDestroy() {//DESTRUI O SERVIÇO
      super.onDestroy()
   }

}