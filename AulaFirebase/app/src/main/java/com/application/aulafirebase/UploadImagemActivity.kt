package com.application.aulafirebase

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.application.aulafirebase.databinding.ActivityUploadImagemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class UploadImagemActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUploadImagemBinding.inflate( layoutInflater )
    }

    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }

    private val armazenamento by lazy {
        FirebaseStorage.getInstance()
    }

    private var uriImagemSelecionada: Uri? = null
    private val abrirGaleria = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){ uri ->
        if (uri != null){
            binding.imageSelecionada.setImageURI( uri )
            uriImagemSelecionada = uri
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

        binding.btnUpload.setOnClickListener {
            uploadGaleria()
        }

        binding.btnRecuperar.setOnClickListener {
            recuperarImagemFirebase()

        }

    }

    private fun recuperarImagemFirebase() {



    }

    private fun uploadGaleria() {

        val idUsuarioLogado = autenticacao.currentUser?.uid
        val nomeImagem = UUID.randomUUID().toString()
        if ( uriImagemSelecionada != null && idUsuarioLogado != null ){
            armazenamento
                .getReference("fotos")
                .child( idUsuarioLogado )
                .child( nomeImagem )
                .putFile( uriImagemSelecionada!! )
                .addOnSuccessListener { task ->
                    Toast.makeText(this, "Sucesso ao fazer upload da imagem", Toast.LENGTH_SHORT).show()
                    task.metadata?.reference?.downloadUrl
                        ?.addOnSuccessListener { uriFirebase ->
                        Toast.makeText(this, uriFirebase.toString(), Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener { erro ->
                    Toast.makeText(this, "Erro ao fazer upload da imagem", Toast.LENGTH_SHORT).show()
                }
        }

    }
}