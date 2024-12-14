package com.app.aulatimeralarmmanagerworkmanager

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import kotlin.math.log

class MeuWork(
  private val context: Context,
  private val workerParameters: WorkerParameters
) : CoroutineWorker( context, workerParameters ) {

  override suspend fun doWork(): Result {
    executarAcao()
    return Result.success()
  }

  private suspend fun executarAcao(){

    setForeground(
      ForegroundInfo(
        System.currentTimeMillis().toInt(),
        NotificationCompat.Builder(applicationContext, Constantes.ID_CANAL)
          .setSmallIcon(R.drawable.ic_lembrete_24)
          .setShowWhen(true)
          .setContentTitle("Lembrete")
          .setContentText("Lembre-se de fazer alguma coisa")
          .build()
      )
    )
    val nome = workerParameters.inputData.getString("nome")
    val tempo = workerParameters.inputData.getInt("tempo", 0)

    Log.i("workmanager_android","nome: $nome tempo: $tempo")

    repeat(5){ contador ->
      delay(1000)
      Log.i("workmanager_android","executando: $contador")
    }
  }

}