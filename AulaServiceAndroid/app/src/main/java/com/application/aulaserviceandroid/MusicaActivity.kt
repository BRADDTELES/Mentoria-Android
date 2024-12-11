package com.application.aulaserviceandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulaserviceandroid.databinding.ActivityMusicaBinding

class MusicaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMusicaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}