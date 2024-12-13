package com.app.aulatimeralarmmanagerworkmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class Agendamento(
   private val context: Context
) {

   private lateinit var alarmeManager: AlarmManager

   fun agendar() {

      val intent = Intent(context, AgendamentoBroadcastReceiver::class.java)

      alarmeManager = context.getSystemService( AlarmManager::class.java )
      alarmeManager.set(
         AlarmManager.RTC,//Data e hora para execução
         System.currentTimeMillis() + 5000,
         PendingIntent.getBroadcast(
            context,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE
         )
      )
   }

   fun cancelar() {

   }
}