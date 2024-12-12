package com.application.aulaserviceandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ComunicacaoBroadcastReceiver : BroadcastReceiver() {

   override fun onReceive(context: Context, intent: Intent) {
      if (intent.action == "com.application.aulaserviceandroid.ABRIR_ARQUIVO_PDF") {
         Log.i("broadcast_android", "abriu arquivo PDF")
      }
   }
}