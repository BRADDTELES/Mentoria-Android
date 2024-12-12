package com.application.aulaserviceandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BroadcastReceiver03 : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Thread.sleep(2000)
        var initialCode = resultCode
        var initialData = resultData
        val extras = getResultExtras(true)
        var dadosExtra = extras.getString("dadosExtra")

        val textoParametro = "[B3] code: $initialCode data: $initialData extras: $dadosExtra"
        Log.i("broadcast_android", "Broadcast 03: $textoParametro")

        //alterar dados
        initialCode++
        initialData += " -> Broadcast3 "
        dadosExtra += " - ALTERADO B3"

        extras.putString("dadosExtra", dadosExtra)
        setResult(
            initialCode,
            initialData,
            extras
        )
    }
}