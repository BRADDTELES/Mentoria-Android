package com.app.aulatimeralarmmanagerworkmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    val oneTimeWorkRequest = OneTimeWorkRequestBuilder<MeuWork>()
      .build()
    val workManager = WorkManager.getInstance( applicationContext )

    binding.btnExecutarWork.setOnClickListener {
      workManager.enqueue( oneTimeWorkRequest )
    }

  }
}