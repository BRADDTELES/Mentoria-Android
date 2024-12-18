package com.application.aulaserviceandroid

import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MinhaConexao : Service() {

   private val coroutine = CoroutineScope( Dispatchers.IO )
   var contador = 0

   inner class CustomBinder : Binder(){
      fun recuperarServico() : MinhaConexao{
         return this@MinhaConexao
      }
   }

   override fun onBind(intent: Intent): IBinder? {
      return CustomBinder()
   }

   override fun onCreate() {
      super.onCreate()
      Log.i("servico_android", "onCreate")
   }


   override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
      Log.i("servico_android", "onStartCommand")

      val idCanal = "notificacaoLembrete"
      val dataAtual = System.currentTimeMillis()
      val notificacao = NotificationCompat.Builder(this, idCanal)
         .apply {
            setSmallIcon(R.drawable.ic_localizacao_24)
            //setWhen( dataAtual )
            setShowWhen(true)
            //setChannelId()
            setLargeIcon(
               BitmapFactory.decodeResource(
                  resources,
                  R.drawable.perfil
               )
            )
            setContentTitle("Acompanhando localização")
            setContentText("Necessário para acompanhar os seus passos")
         }

      startForeground(1, notificacao.build())


      coroutine.launch {
         repeat(20){ i ->
            contador = i
            //sleep(2000)
            delay(2000)
            Log.i("servico_android", "executando: $contador")
         }
         stopSelf()//Parar o serviço
      }

      return super.onStartCommand(intent, flags, startId)
      //return START_NOT_STICKY
      //return START_REDELIVER_INTENT
   }

   override fun onDestroy() {
      Log.i("servico_android", "onDestroy")
      coroutine.cancel()
      super.onDestroy()
   }
}