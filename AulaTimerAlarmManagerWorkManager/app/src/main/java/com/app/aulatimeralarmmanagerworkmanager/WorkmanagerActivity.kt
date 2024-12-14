package com.app.aulatimeralarmmanagerworkmanager

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.app.aulatimeralarmmanagerworkmanager.databinding.ActivityWorkmanagerBinding

class WorkmanagerActivity : AppCompatActivity() {

  private val binding by lazy {
      ActivityWorkmanagerBinding.inflate( layoutInflater )
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView( binding.root )

    solicitarPermissao()

    val oneTimeWorkRequest = OneTimeWorkRequestBuilder<MeuWork>()
      .setInputData( workDataOf("nome" to "jamilton", "tempo" to 1000) )
      //.addTag("execucaoUsuarios")
      /*.setConstraints(
        Constraints.Builder()
          //.setRequiredNetworkType( NetworkType.UNMETERED )
          //.setRequiresCharging(true)
          //.setRequiresBatteryNotLow(true)
          //.setRequiresDeviceIdle(true)
          //.setRequiresStorageNotLow(true)
          .build()
      )*/
      .build()
    val workManager = WorkManager.getInstance( applicationContext )

    //Informações
    workManager
      .getWorkInfoByIdLiveData( oneTimeWorkRequest.id )
      .observe(this){ workInfo ->
        //Log.i("workmanager_android","dados: $workInfo")
        if ( workInfo != null ) {

          val dadosProgresso = workInfo.progress
          val progresso = dadosProgresso.getInt("progresso", 0)
          Log.i("workmanager_android","progresso: $progresso")
        }
      }

    binding.btnExecutarWork.setOnClickListener {
      workManager.enqueue( oneTimeWorkRequest )
      /*workManager.enqueueUniqueWork(
        "nomeTrabalhoExecucaoUsuario",
        ExistingWorkPolicy.KEEP, //tarefa1 - tarefa2
        oneTimeWorkRequest
      )*/
    }

    binding.btnCancelarWork.setOnClickListener {
      workManager.cancelWorkById( oneTimeWorkRequest.id )
      //workManager.cancelAllWorkByTag( "execucaoUsuarios" )
      //workManager.cancelUniqueWork("nomeTrabalhoExecucaoUsuario")
      //workManager.cancelAllWork()
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