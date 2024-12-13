package com.app.aulatimeralarmmanagerworkmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar

class Agendamento(
   private val context: Context
) {

   private lateinit var alarmeManager: AlarmManager

   fun agendar() {

      val intent = Intent(context, AgendamentoBroadcastReceiver::class.java)

      val calendar = Calendar.getInstance().apply {
         timeInMillis = System.currentTimeMillis()// 20/10/2025 10:20:30
         //set(2025, 0, 10)
         /*set(Calendar.YEAR, 2025)
         set(Calendar.MONTH, 0)
         set(Calendar.HOUR_OF_DAY, 22)*/
         //add(Calendar.YEAR, 1)// 20/10/2026 10:20:30
         //add(Calendar.SECOND, 10)// 20/10/2025 10:20:40
         //set(Calendar.HOUR_OF_DAY, 11)
         //set(Calendar.MINUTE, 20)
      }

      val calendarIntervalo = Calendar.getInstance().apply {
         set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
      }

      alarmeManager = context.getSystemService( AlarmManager::class.java )
      alarmeManager.setInexactRepeating(// set -> é um Alarme Inexato
         AlarmManager.RTC,
         calendar.timeInMillis, // 20/10/2025 10:20:30
         //calendarIntervalo.timeInMillis,
         //AlarmManager.INTERVAL_DAY,
         5000,// 5 segundos
         PendingIntent.getBroadcast(
            context,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE
         )
      )
      /*alarmeManager.set(
         AlarmManager.RTC_WAKEUP,//Data e hora para execução
         calendar.timeInMillis,
         PendingIntent.getBroadcast(
            context,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE
         )
      )*/
   }

   fun cancelar() {

   }
}