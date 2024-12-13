package com.app.aulatimeralarmmanagerworkmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AgendamentoBroadcastReceiver : BroadcastReceiver() {

   override fun onReceive(context: Context, intent: Intent) {
      Log.i("agendamento_android", "Executou broadcast agendamento")
   }
}