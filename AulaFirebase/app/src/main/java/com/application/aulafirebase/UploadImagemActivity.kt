package com.application.aulafirebase

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.application.aulafirebase.databinding.ActivityUploadImagemBinding
import com.application.aulafirebase.helper.Permissao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream
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
    private var bitmapImagemSelecionada: Bitmap? = null
    private val abrirGaleria = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){ uri ->
        if (uri != null){
            binding.imageSelecionada.setImageURI( uri )
            uriImagemSelecionada = uri
            Log.d("info_upload", "URI da imagem: $uri")
            Toast.makeText(this, "imagem selecionada", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Nenhuma imagem selecionada", Toast.LENGTH_SHORT).show()
        }
    }
    private val abrirCamera = registerForActivityResult(
        //ActivityResultContracts.GetContent()
        ActivityResultContracts.StartActivityForResult()
    ){ resultadoActivity ->
        //if ( resultadoActivity.resultCode == RESULT_OK ){ }else{ }
        bitmapImagemSelecionada = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            resultadoActivity.data?.extras
                ?.getParcelable("data", Bitmap::class.java)
        }else{
            resultadoActivity.data?.extras
                ?.getParcelable("data")
        }
        binding.imageSelecionada.setImageBitmap( bitmapImagemSelecionada )
    }

    private val permissoes = listOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)

        Log.i("permissao_app", "requestCode: $requestCode")

        permissions.forEachIndexed { indice, valor ->
            Log.i("permissao_app", "permission: $indice) $valor")
        }

        grantResults.forEachIndexed { indice, valor ->
            Log.i("permissao_app", "concedida: $indice) $valor")
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        Permissao.requisitarPermissoes(
            this, permissoes, 100
        )

        binding.btnGaleria.setOnClickListener {
            abrirGaleria.launch("image/*")//Mime Type
        }

        binding.btnCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            abrirCamera.launch( intent )
        }

        binding.btnUpload.setOnClickListener {
            //uploadGaleria()
            Log.d("info_upload", "Iniciando upload da imagem após o clique UPLOAD")
            uploadCamera()
        }

        binding.btnRecuperar.setOnClickListener {
            recuperarImagemFirebase()
        }

    }

    private fun recuperarImagemFirebase() {

        val idUsuarioLogado = autenticacao.currentUser?.uid
        val nomeImagem = UUID.randomUUID().toString()
        if ( idUsuarioLogado!= null ){
            armazenamento
                .getReference("fotos")
                .child( idUsuarioLogado )
                .child( "foto.jpg" )
                //.child( nomeImagem )
                .downloadUrl
                .addOnSuccessListener { urlFirebase ->
                    //binding.imageRecuperada.setImageURI( urlFirebase )
                    Picasso.get()
                        .load(urlFirebase)
                        .into(binding.imageRecuperada)
                    Log.d("info_upload", "Imagem recuperada com sucesso")
                }
        }
    }

    private fun uploadCamera() {

        val idUsuarioLogado = autenticacao.currentUser?.uid
        Log.d("info_upload", "ID do usuário Logado ? $idUsuarioLogado")
        //val nomeImagem = UUID.randomUUID().toString()

        val outputStream = ByteArrayOutputStream()
        bitmapImagemSelecionada?.compress(
            CompressFormat.JPEG,
            80,
            outputStream
        )

        if ( bitmapImagemSelecionada != null && idUsuarioLogado != null ){
            armazenamento
                .getReference("fotos")
                .child( idUsuarioLogado )
                .child( "foto.jpg" )
                //.child( nomeImagem )
                .putBytes( outputStream.toByteArray() )
                .addOnSuccessListener { task ->
                    //Log.d("info_upload", "Com nome da imagem: $nomeImagem")
                    Log.d("info_upload", "Upload da imagem bem-sucedido")
                    Toast.makeText(this, "Sucesso ao fazer upload da imagem", Toast.LENGTH_SHORT).show()
                    task.metadata?.reference?.downloadUrl
                        ?.addOnSuccessListener { uriFirebase ->
                            Toast.makeText(this, uriFirebase.toString(), Toast.LENGTH_SHORT).show()
                        }
                }.addOnFailureListener { erro ->
                    Log.e("info_upload", "Erro ao fazer upload da imagem", erro)
                    Toast.makeText(this, "Erro ao fazer upload da imagem $erro", Toast.LENGTH_SHORT).show()
                }
        }

    }

    private fun uploadGaleria() {

        val idUsuarioLogado = autenticacao.currentUser?.uid
        Log.d("info_upload", "ID do usuário Logado ? $idUsuarioLogado")
        val nomeImagem = UUID.randomUUID().toString()
        if ( uriImagemSelecionada != null && idUsuarioLogado != null ){
            armazenamento
                .getReference("fotos")
                .child( idUsuarioLogado )
                .child( "foto.jpg" )
                //.child( nomeImagem )
                .putFile( uriImagemSelecionada!! )
                .addOnSuccessListener { task ->
                    //Log.d("info_upload", "Com nome da imagem: $nomeImagem")
                    Log.d("info_upload", "Upload da imagem bem-sucedido")
                    Toast.makeText(this, "Sucesso ao fazer upload da imagem", Toast.LENGTH_SHORT).show()
                    task.metadata?.reference?.downloadUrl
                        ?.addOnSuccessListener { uriFirebase ->
                        Toast.makeText(this, uriFirebase.toString(), Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener { erro ->
                    Log.e("info_upload", "Erro ao fazer upload da imagem", erro)
                    Toast.makeText(this, "Erro ao fazer upload da imagem $erro", Toast.LENGTH_SHORT).show()
                }
        }

    }
}