package com.app.aulatimeralarmmanagerworkmanager

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.app.aulatimeralarmmanagerworkmanager.api.CustomRetrofit
import kotlinx.coroutines.delay
import kotlin.math.log

class MeuWork(
  private val context: Context,
  private val workerParameters: WorkerParameters
) : CoroutineWorker( context, workerParameters ) {

  override suspend fun doWork(): Result {

    setForeground(
      ForegroundInfo(
        System.currentTimeMillis().toInt(),
        NotificationCompat.Builder(applicationContext, Constantes.ID_CANAL)
          .setSmallIcon(R.drawable.ic_lembrete_24)
          .setShowWhen(true)
          .setContentTitle("Últimas postagens")
          .setContentText("Recuperando postagens da web")
          .build()
      )
    )

    val jsonPlaceAPI = CustomRetrofit.jsonPlaceApi
    val resposta = jsonPlaceAPI.recuperarPostagens()

    if (resposta != null && resposta.isSuccessful) {
      
      resposta?.body().let { listaPostagem ->
        listaPostagem?.forEach { postagem ->
          delay(250)
          Log.i("workmanager_android","${postagem.id}) ${postagem.title}\n")
        }
      }

      return Result.success( workDataOf("sucesso" to "Sucesso ao recuperar postagem") )

    }else{

      if (  resposta.code().toString().startsWith("5")  ) {
          Result.retry()
      }else{
        return Result.failure( workDataOf("erro" to "Erro ao recuperar postagem") )
      }

    }
    return Result.failure( workDataOf("erro" to "Erro ao recuperar postagem") )
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
    /*val nome = workerParameters.inputData.getString("nome")
    val tempo = workerParameters.inputData.getInt("tempo", 0)*/
    //Log.i("workmanager_android","nome: $nome tempo: $tempo")
    setProgress( workDataOf("progresso" to 0) )
    repeat(100){ contador ->
      delay(1000)
      setProgress( workDataOf("progresso" to contador) )
      Log.i("workmanager_android","executando: $contador")
    }
  }

}