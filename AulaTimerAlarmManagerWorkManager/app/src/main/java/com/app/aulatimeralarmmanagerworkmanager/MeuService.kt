package com.app.aulatimeralarmmanagerworkmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MeuService : Service() {

    val coroutineScope = CoroutineScope( Dispatchers.IO )

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notificacaoBuilder = NotificationCompat.Builder(applicationContext, Constantes.ID_CANAL).apply {
            setSmallIcon(R.drawable.ic_lembrete_24)
            setShowWhen(true)
            setContentTitle("Lembrete")
            setContentText("Lembre-se de fazer alguma coisa")
        }
        startForeground(1, notificacaoBuilder.build())
        executarAcao()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun executarAcao() {

        coroutineScope.launch {
            repeat(5){ contador ->
                delay(1000L)
                Log.i("agendamento_android", "ação: $contador")
            }
        }

    }

    override fun onDestroy() {
        coroutineScope.cancel()
        super.onDestroy()
    }
}