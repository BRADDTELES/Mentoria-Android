package com.application.aulaserviceandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ReinicializacaoReceiver : BroadcastReceiver() {

   override fun onReceive(context: Context, intent: Intent) {
      if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
         Log.i("broadcast_android", "Celular foi reiniciado COMPLETAMENTE")
      }
   }
}