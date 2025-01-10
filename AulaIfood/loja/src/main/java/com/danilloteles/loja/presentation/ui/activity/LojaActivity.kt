package com.danilloteles.loja.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.loja.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LojaActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_loja)
   }
}