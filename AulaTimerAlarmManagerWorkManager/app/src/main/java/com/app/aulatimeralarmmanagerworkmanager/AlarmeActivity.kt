package com.app.aulatimeralarmmanagerworkmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.aulatimeralarmmanagerworkmanager.databinding.ActivityAlarmeBinding

class AlarmeActivity : AppCompatActivity() {

   private val binding by lazy {
       ActivityAlarmeBinding.inflate( layoutInflater )
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )

      binding.btnAgendamento.setOnClickListener {  }
      binding.btnCancelarAgendamento.setOnClickListener {  }
   }
}