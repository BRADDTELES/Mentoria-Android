package com.app.aulatimeralarmmanagerworkmanager

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
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
      .build()
    val workManager = WorkManager.getInstance( applicationContext )

    binding.btnExecutarWork.setOnClickListener {
      workManager.enqueue( oneTimeWorkRequest )
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