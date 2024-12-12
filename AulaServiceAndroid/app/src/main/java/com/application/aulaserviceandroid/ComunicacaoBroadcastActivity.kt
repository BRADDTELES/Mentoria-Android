package com.application.aulaserviceandroid

import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ComunicacaoBroadcastActivity : AppCompatActivity() {

   private lateinit var comunicacaoBroadcastReceiver: ComunicacaoBroadcastReceiver

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_comunicacao_broadcast)

      comunicacaoBroadcastReceiver = ComunicacaoBroadcastReceiver()
      IntentFilter(
         "com.application.aulaserviceandroid.ABRIR_ARQUIVO_PDF"
      ).apply {
         registerReceiver( comunicacaoBroadcastReceiver, this )
      }

   }

   override fun onDestroy() {
      unregisterReceiver( comunicacaoBroadcastReceiver )
      super.onDestroy()
   }
}