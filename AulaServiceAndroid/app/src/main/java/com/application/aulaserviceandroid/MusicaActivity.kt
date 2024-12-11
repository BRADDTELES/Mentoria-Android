package com.application.aulaserviceandroid

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
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
        inicializarControleVolume()
        executarServicoMusica()
    }

    private fun executarServicoMusica() {

        val musicaService = Intent(this, MusicaService::class.java)
        binding.btnIniciarServicoMusica.setOnClickListener {
            startService( musicaService )
        }

        binding.btnPararSercvicoMusica.setOnClickListener {
            stopService( musicaService )
        }

    }

    private fun inicializarControleVolume() {

        //val audioManager = getSystemService( Context.AUDIO_SERVICE ) as AudioManager
        val audioManager = getSystemService( AudioManager::class.java )

        binding.seekVolume.max = audioManager
            .getStreamMaxVolume( AudioManager.STREAM_MUSIC )
        binding.seekVolume.progress = audioManager
            .getStreamVolume( AudioManager.STREAM_MUSIC )

        binding.seekVolume.setOnSeekBarChangeListener( object  : OnSeekBarChangeListener{
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {

                audioManager.setStreamVolume(
                    AudioManager.STREAM_MUSIC,
                    progress,
                    0
                )

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

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