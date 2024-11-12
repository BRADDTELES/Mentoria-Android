package com.application.aulawhatsapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.application.aulawhatsapp.databinding.ActivityMainBinding
import com.application.aulawhatsapp.databinding.ActivityPerfilBinding
import com.application.aulawhatsapp.utils.exibirMensagem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class PerfilActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPerfilBinding.inflate(layoutInflater)
    }
    private var temPermissaoCamera = false
    private var temPermissaoGaleria = false

    private val gerenciadorGaleria = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){ uri ->
        if (uri != null){
            binding.imgPerfil.setImageURI( uri )
            uploadImagemStorage( uri )
        }else{
            exibirMensagem("Nenhuma imagem selecionada")
        }
    }
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val storage by lazy {
        FirebaseStorage.getInstance()
    }
    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializarToolbar()
        solicitarPermissoes()
        inicializarEventosClique()
    }

    private fun uploadImagemStorage(uri: Uri) {

        // fotos -> usuarios -> id_usuario -> perfil.jpg
        val idUsuario = firebaseAuth.currentUser?.uid
        if (idUsuario != null){

            storage
                .getReference("fotos")
                .child("usuarios")
                .child(idUsuario)
                .child("perfil.jpg")
                .putFile(uri)
                .addOnSuccessListener { task ->

                    exibirMensagem("Sucesso ao fazer upload da imagem")
                    task.metadata
                        ?.reference
                        ?.downloadUrl
                        ?.addOnSuccessListener { url ->

                            val dados = mapOf(
                                "foto" to url.toString()
                            )
                            atualizarDadosPerfil( idUsuario, dados)

                        }
                }.addOnFailureListener {
                    exibirMensagem("Erro ao fazer upload da imagem")
                }
        }

    }

    private fun atualizarDadosPerfil(idUsuario: String, dados: Map<String, String>) {

        firestore
            .collection("usuarios")
            .document( idUsuario )
            .update( dados )
            .addOnSuccessListener {
                exibirMensagem("Sucesso ao atualizar perfil!")
            }
            .addOnFailureListener {
                exibirMensagem("Erro ao atualizar perfil!")
            }

    }

    private fun inicializarEventosClique() {

        binding.fabSelecionar.setOnClickListener {
            if ( temPermissaoGaleria ){
                gerenciadorGaleria.launch("image/*")
            }else{
                exibirMensagem("Não tem permissão para acessar galeria")
                solicitarPermissoes()
            }
        }

        binding.btnAtualizar.setOnClickListener {

            val nomeUsuario = binding.editNomePerfil.text.toString()
            if (nomeUsuario.isNotEmpty()){

                val idUsuario = firebaseAuth.currentUser?.uid
                if (idUsuario != null){
                    val dados = mapOf(
                        "nome" to nomeUsuario
                    )
                    atualizarDadosPerfil( idUsuario, dados)
                }

            }else{
                exibirMensagem("Preencha o nome para atualizar")
            }

        }

    }

    private fun solicitarPermissoes() {

        //Verificar permissões
        temPermissaoCamera = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        temPermissaoGaleria = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED

        //LISTA DE PERMISSÇOES NEGADAS
        val listaPermissoesNegadas = mutableListOf<String>()
        if ( !temPermissaoCamera )
            listaPermissoesNegadas.add( Manifest.permission.CAMERA )

        if ( !temPermissaoGaleria )
            listaPermissoesNegadas.add( Manifest.permission.READ_MEDIA_IMAGES )

        if ( listaPermissoesNegadas.isNotEmpty() ){
            //Solicitar multiplas permissões
            val gerenciadorPermissoes = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ){ permissoes ->

                temPermissaoCamera = permissoes[Manifest.permission.CAMERA]
                    ?: temPermissaoCamera

                temPermissaoGaleria = permissoes[Manifest.permission.READ_MEDIA_IMAGES]
                    ?: temPermissaoGaleria

            }
            gerenciadorPermissoes.launch( listaPermissoesNegadas.toTypedArray() )
        }

    }

    private fun inicializarToolbar() {
        val toolbar = binding.includeToolbarPerfil.tbPrincipal
        setSupportActionBar( toolbar )
        supportActionBar?.apply {
            title = "Editar perfil"
            setDisplayHomeAsUpEnabled(true)
        }
    }
}