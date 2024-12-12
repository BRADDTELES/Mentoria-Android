package com.application.aulaserviceandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BroadcastReceiver01 : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Thread.sleep(2000)
        Log.i("broadcast_android", "Broadcast 01")
    }
}