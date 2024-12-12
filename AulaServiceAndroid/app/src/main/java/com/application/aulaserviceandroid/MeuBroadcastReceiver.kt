package com.application.aulaserviceandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MeuBroadcastReceiver : BroadcastReceiver() {

   override fun onReceive(context: Context, intent: Intent) {


      val mensagem = when (intent.action) {
         Intent.ACTION_AIRPLANE_MODE_CHANGED -> "ACTION_AIRPLANE_MODE_CHANGED"
         Intent.ACTION_BATTERY_CHANGED       -> "ACTION_BATTERY_CHANGED"
         Intent.ACTION_POWER_CONNECTED       -> "ACTION_POWER_CONNECTED"
         Intent.ACTION_POWER_DISCONNECTED    -> "ACTION_POWER_DISCONNECTED"
         else -> ""
      }
      Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show()

   }
}