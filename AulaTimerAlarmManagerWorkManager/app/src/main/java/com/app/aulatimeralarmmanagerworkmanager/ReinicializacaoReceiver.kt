package com.app.aulatimeralarmmanagerworkmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ReinicializacaoReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (  intent.action == Intent.ACTION_BOOT_COMPLETED  ) {
            Log.i("agendamento_android", "reinicializado")
        }
    }
}