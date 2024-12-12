package com.application.aulaserviceandroid

import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class BroadcastOrdenadoActivity : AppCompatActivity() {

    private val broadcastReceiver01 = BroadcastReceiver01()
    private val broadcastReceiver02 = BroadcastReceiver02()
    private val broadcastReceiver03 = BroadcastReceiver03()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_ordenado)

        IntentFilter().apply {
            addAction("com.application.aulaserviceandroid.ACAO_ORDENADA")
            priority = 3
        }.also { intentFilter ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver( broadcastReceiver01, intentFilter, RECEIVER_EXPORTED)
            }else{
                registerReceiver( broadcastReceiver01, intentFilter )
            }
        }
        IntentFilter().apply {
            addAction("com.application.aulaserviceandroid.ACAO_ORDENADA")
            priority = 2
        }.also { intentFilter ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver( broadcastReceiver02, intentFilter, RECEIVER_EXPORTED)
            }else{
                registerReceiver( broadcastReceiver02, intentFilter )
            }
        }
        IntentFilter().apply {
            addAction("com.application.aulaserviceandroid.ACAO_ORDENADA")
            priority = 1
        }.also { intentFilter ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                registerReceiver( broadcastReceiver03, intentFilter, RECEIVER_EXPORTED)
            }else{
                registerReceiver( broadcastReceiver03, intentFilter )
            }
        }

    }

    override fun onDestroy() {
        unregisterReceiver( broadcastReceiver01 )
        unregisterReceiver( broadcastReceiver02 )
        unregisterReceiver( broadcastReceiver03 )
        super.onDestroy()
    }
}