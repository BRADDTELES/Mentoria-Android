package com.app.aulatimeralarmmanagerworkmanager

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class CustomApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    //Criar canal
    val notificationManager = applicationContext.getSystemService( NotificationManager::class.java )

    if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
      val canal = NotificationChannel(
        Constantes.ID_CANAL,
        Constantes.NOME_CANAL,
        NotificationManager.IMPORTANCE_HIGH
      )
      notificationManager.createNotificationChannel( canal )
    }
  }
}