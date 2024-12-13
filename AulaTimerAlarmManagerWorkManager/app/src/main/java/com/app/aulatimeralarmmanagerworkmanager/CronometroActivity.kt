package com.app.aulatimeralarmmanagerworkmanager

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.app.aulatimeralarmmanagerworkmanager.databinding.ActivityCronometroBinding

class CronometroActivity : AppCompatActivity() {

   private val binding by lazy {
       ActivityCronometroBinding.inflate( layoutInflater )
   }
   private lateinit var countDownTimer: CountDownTimer //Temporizador
   private var executandoCronometro = false
   private var tempoMilisegundos = 60000L

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )

      binding.btnIniciarCronometro.setOnClickListener {
         if (executandoCronometro) {
            pausarCronometro()
         }else{
            val tempoDigitado = binding.editTempo.text.toString()
            tempoMilisegundos = tempoDigitado.toLong() * 60000L
            iniciarCronometro( tempoMilisegundos )
         }
      }

      binding.btnReiniciarCronometro.setOnClickListener {
         reiniciarCronometro()
      }

   }

   private fun reiniciarCronometro() {
      tempoMilisegundos = 60000L
      binding.textResultado.text = "00:00"
      binding.btnReiniciarCronometro.visibility = View.INVISIBLE
   }

   private fun iniciarCronometro(tempoMilisegundosParam: Long) {

      countDownTimer = object : CountDownTimer(this.tempoMilisegundos, 1000L){
         override fun onTick(millisUntilFinished: Long) {
            tempoMilisegundos = millisUntilFinished
            atualizarInterface()
         }

         override fun onFinish() {
            binding.textResultado.text = "Finalizado"
         }

      }
      //Iniciar o cronometro
      countDownTimer.start()
      binding.btnIniciarCronometro.text = "Parar Cronômetro"
      executandoCronometro = true
      binding.btnReiniciarCronometro.visibility = View.INVISIBLE

   }

   private fun atualizarInterface() {

      val minutos = (tempoMilisegundos / 1000) / 60
      val segundos = (tempoMilisegundos / 1000) % 60//0 até 59

      binding.textResultado.text = "$minutos:$segundos"

   }

   private fun pausarCronometro() {
      binding.btnIniciarCronometro.text = "Iniciar Cronômetro"
      executandoCronometro = false
      binding.btnReiniciarCronometro.visibility = View.VISIBLE
      countDownTimer.cancel()
   }

   override fun onStop() {
      super.onStop()
      countDownTimer.cancel()
   }
}