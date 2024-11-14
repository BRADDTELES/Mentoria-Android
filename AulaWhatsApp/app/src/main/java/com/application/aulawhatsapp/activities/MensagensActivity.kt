package com.application.aulawhatsapp.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.aulawhatsapp.adapters.MensagensAdapter
import com.application.aulawhatsapp.databinding.ActivityMensagensBinding
import com.application.aulawhatsapp.model.Conversa
import com.application.aulawhatsapp.model.Mensagem
import com.application.aulawhatsapp.model.Usuario
import com.application.aulawhatsapp.utils.Constantes
import com.application.aulawhatsapp.utils.exibirMensagem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.squareup.picasso.Picasso

class MensagensActivity : AppCompatActivity() {

    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }
    private val binding by lazy {
        ActivityMensagensBinding.inflate(layoutInflater)
    }
    private lateinit var listenerRegistration: ListenerRegistration
    private var dadosDestinatario: Usuario? = null
    private var dadosUsuarioRemetente: Usuario? = null
    private lateinit var conversasAdapter: MensagensAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        recuperarDadosUsuarios()
        inicializarToolbar()
        inicializarEventoClique()
        inicializarListeners()
        inicializarRecyclerview()
    }

    private fun inicializarRecyclerview() {

        with(binding){
            conversasAdapter = MensagensAdapter()
            rvMensagens.adapter = conversasAdapter
            rvMensagens.layoutManager = LinearLayoutManager(applicationContext)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        listenerRegistration.remove()
    }

    private fun inicializarListeners() {

        val idUsuarioRemetente = firebaseAuth.currentUser?.uid
        val idUsuarioDestinatario = dadosDestinatario?.id
        if (idUsuarioRemetente != null && idUsuarioDestinatario != null){

            listenerRegistration = firestore
                .collection(Constantes.MENSAGENS)
                .document( idUsuarioRemetente )
                .collection( idUsuarioDestinatario )
                .orderBy("data", Query.Direction.ASCENDING)
                .addSnapshotListener { querySnapshot, erro ->

                    if (erro != null){
                        exibirMensagem("Erro ao recuperar mensagens")
                    }

                    val listaMensagens = mutableListOf<Mensagem>()
                    val documentos = querySnapshot?.documents

                    documentos?.forEach { documentSnapshot ->
                        val mensagem = documentSnapshot.toObject( Mensagem::class.java )
                        if (mensagem != null){
                            listaMensagens.add(mensagem)
                            Log.i("exibicao_mensagens", mensagem.mensagem)
                        }
                    }

                    //Lista
                    if (listaMensagens.isNotEmpty()){
                        //Carregar os dados Adapter
                        conversasAdapter.adicionarLista( listaMensagens )
                    }

                }
        }


    }

    private fun inicializarEventoClique() {

        binding.fabEnviar.setOnClickListener {
            val mensagem = binding.editMensagem.text.toString()
            salvarMensagem(mensagem)
        }

    }

    private fun salvarMensagem(textoMensagem: String) {

        if (textoMensagem.isNotEmpty()){

            val idUsuarioRemetente = firebaseAuth.currentUser?.uid
            val idUsuarioDestinatario = dadosDestinatario?.id
            if (idUsuarioRemetente != null && idUsuarioDestinatario != null){
                val mensagem = Mensagem(
                    idUsuarioRemetente, textoMensagem
                )

                //Salvar mensagem para o remetente
                salvarMensagemFirestore(
                    idUsuarioRemetente, idUsuarioDestinatario, mensagem
                )
                //Jamilton -> Foto e nome Destinatario (ana)
                val conversaRemetente = Conversa(
                    idUsuarioRemetente, idUsuarioDestinatario,
                    dadosDestinatario!!.foto, dadosDestinatario!!.nome,
                    textoMensagem
                )
                salvarConversaFirestore( conversaRemetente )

                //Salvar mensagem para o destinatário
                salvarMensagemFirestore(
                    idUsuarioDestinatario, idUsuarioRemetente, mensagem
                )
                //Ana -> Foto e nome Remetente (jamilton)
                val conversaDestinatario = Conversa(
                    idUsuarioDestinatario, idUsuarioRemetente,
                    dadosUsuarioRemetente!!.foto, dadosUsuarioRemetente!!.nome,
                    textoMensagem
                )
                salvarConversaFirestore( conversaDestinatario )

                binding.editMensagem.setText("")

            }
        }

    }

    private fun salvarConversaFirestore(conversa: Conversa) {

        firestore
            .collection( Constantes.CONVERSAS )
            .document( conversa.idUsuarioRemetente )
            .collection( Constantes.ULTIMAS_CONVERSAS )
            .document( conversa.idUsuarioDestinatario )
            .set( conversa )
            .addOnFailureListener {
                exibirMensagem("Erro ao salvar conversa")
            }

    }

    private fun salvarMensagemFirestore(
        idUsuarioRemetente: String,
        idUsuarioDestinatario: String,
        mensagem: Mensagem
    ) {
        firestore
            .collection(Constantes.MENSAGENS)
            .document(idUsuarioRemetente)
            .collection(idUsuarioDestinatario)
            .add( mensagem )
            .addOnFailureListener {
                exibirMensagem("Erro ao enviar mensagem")
            }
    }

    private fun inicializarToolbar() {
        val toolbar = binding.tbMensagens
        setSupportActionBar( toolbar )
        supportActionBar?.apply {
            title = ""
            if (dadosDestinatario != null) {
                binding.textNome.text = dadosDestinatario!!.nome
                Picasso.get()
                    .load(dadosDestinatario!!.foto)
                    .into(binding.imageFotoPerfil)
            }
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun recuperarDadosUsuarios() {

        //Dados do usuaŕio logado
        val idUsuarioRemetente = firebaseAuth.currentUser?.uid
        if ( idUsuarioRemetente != null ){
            firestore
                .collection(Constantes.USUARIOS)
                .document(idUsuarioRemetente)
                .get()
                .addOnSuccessListener {  documentSnapshot ->

                    val usuario = documentSnapshot.toObject(Usuario::class.java)
                    if ( usuario != null ){
                        dadosUsuarioRemetente = usuario
                    }

                }
        }


        //Recuperando dados destinatario
        val extras = intent.extras
        if (extras != null){

            val origem = extras.getString("origem")
            if (origem == Constantes.ORIGEM_CONTATO){

                dadosDestinatario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    extras.getParcelable(
                        "dadosDestinatario",
                        Usuario::class.java
                    )
                }else{
                    extras.getParcelable(
                        "dadosDestinatario"
                    )
                }

            }else if (origem == Constantes.ORIGEM_CONVERSA){
                //Recuperar dados da conversa
            }

        }

    }
}