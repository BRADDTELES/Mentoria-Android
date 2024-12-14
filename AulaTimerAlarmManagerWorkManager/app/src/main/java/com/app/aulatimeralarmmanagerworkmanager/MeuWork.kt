package com.app.aulatimeralarmmanagerworkmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
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
    repeat(5){ contador ->
      delay(1000)
      Log.i("workmanager_android","executando: $contador")
    }
  }

}