package com.application.aulafirebase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.application.aulafirebase.databinding.ActivityUploadImagemBinding

class UploadImagemActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUploadImagemBinding.inflate( layoutInflater )
    }
    private val abrirGaleria = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){ uri ->
        if (uri != null){
            binding.imageSelecionada.setImageURI( uri )
            Toast.makeText(this, "imagem selecionada", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Nenhuma imagem selecionada", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        binding.btnGaleria.setOnClickListener {
            abrirGaleria.launch("image/*")//Mime Type
        }

    }
}