package com.application.projetoturmafirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.projetoturmafirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }
    private val bancoDados by lazy {
        FirebaseFirestore.getInstance()
    }
    private lateinit var viagemAdapter: ViagemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        adicionarListeners()

        with(binding){
            btnDeslogar.setOnClickListener {
                autenticacao.signOut()
                startActivity(
                    Intent(applicationContext, LoginActivity::class.java) //em uma função de escopo, a Intent só funciona com o applicationContext e não com o this
                )
            }

            binding.btnSalvar.setOnClickListener {
                salvarViagemUsuario()
            }
            viagemAdapter = ViagemAdapter()
            rvViagens.adapter = viagemAdapter
            rvViagens.layoutManager = LinearLayoutManager(applicationContext)
        }

    }

    private fun adicionarListeners() {
        adicionarListenersViagens()
        //Vários outros listeners

    }

    private fun adicionarListenersViagens() {

        val refViagens = bancoDados
            .collection("viagens")
            //.document("4jCpfmih00KV5tS1TsAF")

        refViagens.addSnapshotListener { querySnapshot, erro ->

            /*val dados = documentSnapshot?.data
            if (dados != null){
                val viagem = dados["viagem"]
            }*/

            val listaDocumentos = querySnapshot?.documents
            val listaViagens = mutableListOf<String>()

            listaDocumentos?.forEach { documentSnapshot ->
                val dados = documentSnapshot?.data
                if (dados != null){
                    val viagem = dados["viagem"].toString()
                    listaViagens.add( viagem )
                    Log.i("info_firebase", "Viagem: $viagem")
                }
            }

            //Lista de viagens
            viagemAdapter.adicionarLista(listaViagens)

        }
    }

    private fun salvarViagemUsuario() {

        val textoViagem = binding.editViagem.text.toString()
        if ( textoViagem.isNotEmpty() ){

            val dados = mapOf(
                "viagem" to textoViagem
            )
            bancoDados
                .collection("viagens")
                //.document("1")
                //.set(dados)
                .add( dados )
                .addOnSuccessListener {
                    exibirMensagem("Viagem salva com sucesso")
                    binding.editViagem.setText("")//limpa o campo após Salvar a viagem
                }.addOnFailureListener { erro ->
                    exibirMensagem("Erro ao salvar Viagem")
                    Log.i("info_firebase", "Erro: ${erro.printStackTrace()}")
                }
        }else{
            exibirMensagem("Preencha local da viagem")
        }


    }

    private fun exibirMensagem(mensagem: String){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}