package com.application.aulafirebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulafirebase.databinding.ActivityUploadImagemBinding

class UploadImagemActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUploadImagemBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )



    }
}