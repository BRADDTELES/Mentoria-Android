package com.application.aulaserviceandroid

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class CustomApplication : Application() {

   override fun onCreate() {
      super.onCreate()
      criarCanais()
   }

   fun criarCanais() {

      val idCanal = "notificacaoLembrete"
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

         val canal = NotificationChannel(
            idCanal,
            "Lembrete",
            NotificationManager.IMPORTANCE_HIGH
         )

         getSystemService( NotificationManager::class.java )
            .createNotificationChannel( canal )
            //.createNotificationChannels( listOf(canal1, canal2...))

      }

   }

}