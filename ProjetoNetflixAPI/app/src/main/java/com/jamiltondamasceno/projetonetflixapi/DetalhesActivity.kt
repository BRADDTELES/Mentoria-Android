package com.jamiltondamasceno.projetonetflixapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jamiltondamasceno.projetonetflixapi.databinding.ActivityDetalhesBinding

class DetalhesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

    }
}