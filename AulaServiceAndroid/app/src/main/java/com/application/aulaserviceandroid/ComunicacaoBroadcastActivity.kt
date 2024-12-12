package com.application.aulaserviceandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.application.aulaserviceandroid.databinding.ActivityComunicacaoBroadcastBinding

class ComunicacaoBroadcastActivity : AppCompatActivity() {

   private val binding by lazy {
      ActivityComunicacaoBroadcastBinding.inflate(layoutInflater)
   }
   private lateinit var comunicacaoBroadcastReceiver: ComunicacaoBroadcastReceiver

   private val broadcastActivity = object : BroadcastReceiver(){
      override fun onReceive(context: Context, intent: Intent?) {

         if (  intent?.action == Intent.ACTION_POWER_CONNECTED  ) {
            Toast.makeText(
               applicationContext,
               "Cabo energia conectado",
               Toast.LENGTH_SHORT).show()

            binding.textInformacao.text = "Cabo energia conectado"
         }else{
            Toast.makeText(
               applicationContext,
               "Cabo energia desconectado",
               Toast.LENGTH_SHORT).show()

            binding.textInformacao.text = "Cabo energia desconectado"
         }
      }

   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )

      binding.btnImplicita.setOnClickListener {

         /*val link = "https://www.google.com/"
         val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
         startActivity(intent)*/

         val intent = Intent()
         intent.action = Intent.ACTION_SEND
         intent.putExtra(Intent.EXTRA_TEXT, "Olá, tudo bem ?")
         intent.type = "plain/text"

         val compartilhar = Intent.createChooser( intent, "Compartilhar")
         startActivity(compartilhar)

      }

      comunicacaoBroadcastReceiver = ComunicacaoBroadcastReceiver()
      IntentFilter(
         "com.application.aulaserviceandroid.ABRIR_ARQUIVO_PDF"
      ).apply {
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver( comunicacaoBroadcastReceiver, this, RECEIVER_NOT_EXPORTED)
         }else{
            registerReceiver( comunicacaoBroadcastReceiver, this)
         }
      }

      //Broadcast Activity
      IntentFilter().apply {
         addAction( Intent.ACTION_POWER_CONNECTED )
         addAction( Intent.ACTION_POWER_DISCONNECTED )
      }.also { intentFilter ->
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver( broadcastActivity, intentFilter, RECEIVER_NOT_EXPORTED)
         }else{
            registerReceiver( broadcastActivity, intentFilter)
         }
         //registerReceiver( broadcastActivity, intentFilter)
      }

   }

   override fun onDestroy() {
      unregisterReceiver( comunicacaoBroadcastReceiver )
      unregisterReceiver( broadcastActivity )
      super.onDestroy()
   }
}