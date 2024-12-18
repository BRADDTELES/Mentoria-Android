package com.danilloteles.aovivoturma02expresso

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.aovivoturma02expresso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnLogar.setOnClickListener {

            startActivity(
                Intent(this, DetalhesActivity::class.java)
            )

        }
    }
}