package com.app.aulatimeralarmmanagerworkmanager

import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.app.aulatimeralarmmanagerworkmanager.databinding.ActivityAlarmeBinding
import java.text.SimpleDateFormat

class AlarmeActivity : AppCompatActivity() {

   private val binding by lazy {
       ActivityAlarmeBinding.inflate( layoutInflater )
   }
   private val agendamento by lazy { Agendamento(this) }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )

      val calendar = Calendar.getInstance().apply {
         timeInMillis = System.currentTimeMillis()// 20/10/2025 10:20:30
         //set(2025, 0, 10)
         /*set(Calendar.YEAR, 2025)
         set(Calendar.MONTH, 0)
         set(Calendar.HOUR_OF_DAY, 22)*/
         add(Calendar.YEAR, 1)// 20/10/2026 10:20:30
         add(Calendar.MINUTE, 10)// 20/10/2025 10:30:30

      }

      //Formatação
      val dataMili = calendar.timeInMillis
      val formatador = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
      val dataFormatada = formatador.format( dataMili )

      Log.i("agendamento_android", "data: $dataFormatada")

      solicitarPermissao()// Solcitar permissão para Android API 33+

      binding.btnAgendamento.setOnClickListener {
         agendamento.agendar()
      }
      binding.btnCancelarAgendamento.setOnClickListener {

      }
   }

   private fun solicitarPermissao() {

      if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ) {

         val permissaoNotificacao = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.POST_NOTIFICATIONS
         )
         if (  permissaoNotificacao == PackageManager.PERMISSION_DENIED  ) {
             ActivityCompat.requestPermissions(
                this,
                arrayOf(// Array de Perimissões
                   android.Manifest.permission.POST_NOTIFICATIONS
                ),
                10
             )
         }

      }

   }
}