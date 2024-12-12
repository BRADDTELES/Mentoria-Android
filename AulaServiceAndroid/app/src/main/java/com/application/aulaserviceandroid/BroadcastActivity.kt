package com.application.aulaserviceandroid

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.application.aulaserviceandroid.databinding.ActivityBroadcastBinding

class BroadcastActivity : AppCompatActivity() {

   private val binding by lazy {
       ActivityBroadcastBinding.inflate( layoutInflater )
   }
   private lateinit var meuBroadcastReceiver: MeuBroadcastReceiver

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContentView( binding.root )
      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
         val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
         insets
      }

      meuBroadcastReceiver = MeuBroadcastReceiver()
      /*val intentFilter = IntentFilter()
      intentFilter.addAction( Intent.ACTION_AIRPLANE_MODE_CHANGED )
      intentFilter.addAction( Intent.ACTION_BATTERY_CHANGED )

      registerReceiver( meuBroadcastReceiver, intentFilter )*/
      IntentFilter().apply {
         addAction( Intent.ACTION_AIRPLANE_MODE_CHANGED )
         addAction( Intent.ACTION_BATTERY_CHANGED )
         addAction( Intent.ACTION_POWER_CONNECTED )
         addAction( Intent.ACTION_POWER_DISCONNECTED )
         addAction( WifiManager.WIFI_STATE_CHANGED_ACTION )
      }.also { intentFilter ->
         registerReceiver( meuBroadcastReceiver, intentFilter )
      }

      //val wifiManager = getSystemService( Context.WIFI_SERVICE ) as WifiManager
      /*val wifiManager = getSystemService( WifiManager::class.java )
      if ( wifiManager.isWifiEnabled ) {
         binding.textResultado.text = "Habilitado"
      }else{
         binding.textResultado.text = "Desabilitado"
      }*/

   }

   override fun onDestroy() {
      unregisterReceiver( meuBroadcastReceiver )
      super.onDestroy()
   }

}