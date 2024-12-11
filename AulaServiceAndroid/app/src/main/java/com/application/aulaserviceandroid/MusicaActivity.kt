package com.application.aulaserviceandroid

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.application.aulaserviceandroid.databinding.ActivityMusicaBinding

class MusicaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMusicaBinding.inflate(layoutInflater)
    }

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializarMediaPlayer()
        inicializarControles()
    }

    private fun inicializarControles() {
        binding.btnTocar.setOnClickListener { play() }
        binding.btnPausar.setOnClickListener { pause() }
        binding.btnParar.setOnClickListener { stop() }
    }

    private fun stop() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            Toast.makeText(this, "parado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun pause() {
        if ( mediaPlayer?.isPlaying == true ) {
            mediaPlayer?.pause()
            Toast.makeText(this, "pausado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun play() {
        if (  mediaPlayer == null  ) {
            inicializarMediaPlayer()
        }
        mediaPlayer?.start()
        Toast.makeText(this, "tocando", Toast.LENGTH_SHORT).show()
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
        }
        super.onDestroy()
    }
}