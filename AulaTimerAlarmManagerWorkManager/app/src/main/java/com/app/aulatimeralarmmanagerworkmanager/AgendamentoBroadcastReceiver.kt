package com.app.aulatimeralarmmanagerworkmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.http.UrlRequest.Builder
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat

class AgendamentoBroadcastReceiver : BroadcastReceiver() {

   override fun onReceive(context: Context, intent: Intent) {
      Log.i("agendamento_android", "Executou broadcast agendamento")
      //exibirNotificacao(context)
   }

   private fun exibirNotificacao(context: Context) {

      //Criar canal
      val idCanal = "lembrete"
      val notificationManager = context.getSystemService( NotificationManager::class.java )
      
      if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
          val canal = NotificationChannel(
             idCanal,
             "Lembretes",
             NotificationManager.IMPORTANCE_HIGH
          )
         notificationManager.createNotificationChannel( canal )
      }

      val notificacaoBuilder = NotificationCompat.Builder(context, idCanal).apply {
         setSmallIcon(R.drawable.ic_lembrete_24)
         setShowWhen(true)
         setContentTitle("Lembrete")
         setContentText("Lembre-se de fazer alguma coisa")
      }
      notificationManager.notify(1, notificacaoBuilder.build() )

   }
}