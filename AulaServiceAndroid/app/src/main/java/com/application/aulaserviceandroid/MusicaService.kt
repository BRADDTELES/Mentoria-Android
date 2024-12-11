package com.application.aulaserviceandroid

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class MusicaService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
        inicializarMediaPlayer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer?.start()
        Toast.makeText(this, "tocando", Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun inicializarMediaPlayer() {
        mediaPlayer = MediaPlayer.create(
            this, R.raw.teste
        )
    }

    override fun onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer?.stop()
            mediaPlayer?.release() //Libera os recursos de memoria
            mediaPlayer = null
            Toast.makeText(this, "parado", Toast.LENGTH_SHORT).show()
        }
        super.onDestroy()
    }
}