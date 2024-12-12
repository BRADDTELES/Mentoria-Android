package com.application.aulaserviceandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.Toast

class MeuBroadcastReceiver : BroadcastReceiver() {

   override fun onReceive(context: Context, intent: Intent) {

      /*val bundle = intent.extras
      val statusWifi = bundle?.getInt(
         WifiManager.EXTRA_WIFI_STATE
      )*/
      val statusWifi = intent.getIntExtra(
         WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN
      )

      val mensagem = when (intent.action) {
         Intent.ACTION_AIRPLANE_MODE_CHANGED -> "ACTION_AIRPLANE_MODE_CHANGED"
         Intent.ACTION_BATTERY_CHANGED       -> "ACTION_BATTERY_CHANGED"
         Intent.ACTION_POWER_CONNECTED       -> "ACTION_POWER_CONNECTED"
         Intent.ACTION_POWER_DISCONNECTED    -> "ACTION_POWER_DISCONNECTED"
         WifiManager.WIFI_STATE_CHANGED_ACTION    -> "WIFI_STATE_CHANGED_ACTION"
         else -> ""
      }

      val mensagemWifi = when (statusWifi) {
         WifiManager.WIFI_STATE_ENABLED -> {
            Log.i("broadcast_android", "Habilitado Wifi")
            "Habilitado Wifi"
         }
         WifiManager.WIFI_STATE_DISABLED -> {
            Log.i("broadcast_android", "Desabilitado Wifi")
            "Desabilitado Wifi"
         }
         else -> {
            Log.i("broadcast_android", "Status: $statusWifi")
            "Prossesando conexão wifi"
         }
      }

      Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show()
      Toast.makeText(context, mensagemWifi, Toast.LENGTH_LONG).show()

   }
}