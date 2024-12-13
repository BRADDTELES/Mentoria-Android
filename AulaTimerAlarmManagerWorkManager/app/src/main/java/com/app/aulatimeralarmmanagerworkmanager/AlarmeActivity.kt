package com.app.aulatimeralarmmanagerworkmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.aulatimeralarmmanagerworkmanager.databinding.ActivityAlarmeBinding

class AlarmeActivity : AppCompatActivity() {

   private val binding by lazy {
       ActivityAlarmeBinding.inflate( layoutInflater )
   }
   private val agendamento by lazy { Agendamento(this) }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )

      binding.btnAgendamento.setOnClickListener {
         agendamento.agendar()
      }
      binding.btnCancelarAgendamento.setOnClickListener {

      }
   }
}