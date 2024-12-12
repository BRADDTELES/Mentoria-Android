package com.application.aulaenviarbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class CapturaResultadoReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        var initialCode = resultCode
        var initialData = resultData
        val extras = getResultExtras(true)
        var dadosExtra = extras.getString("dadosExtra")

        val textoParametro = "[CAPTURA] code: $initialCode data: $initialData extras: $dadosExtra"
        Log.i("broadcast_android", "Broadcast Captura: $textoParametro")

    }
}