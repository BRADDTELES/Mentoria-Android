package com.danilloteles.aulaacoesintent

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.danilloteles.aulaacoesintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        verificarPermissao()

        binding.btnExecutar.setOnClickListener {
            //chamadaTelefonica()
            compartilharTexto()
        }
    }

    private fun compartilharTexto() {

        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, "Olá, Danillo")
            type = "text/plain"
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val intentCompartilhar = Intent.createChooser(intent, "Compartilhar")
        startActivity(intentCompartilhar)

    }

    private fun verificarPermissao() {

        val permissaoLicacao = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CALL_PHONE
        )

        if (  permissaoLicacao == PackageManager.PERMISSION_DENIED  ) {

            val listaPermissoes = arrayOf(android.Manifest.permission.CALL_PHONE)
            ActivityCompat.requestPermissions(
                this,
                listaPermissoes,
                100  // request code
            )

        }

    }

    private fun chamadaTelefonica() {

        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:+5511995634532")
        }
        startActivity(intent)

    }
}