package com.application.aulafirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.application.aulafirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }
    private val bancoDados by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        binding.btnExecutar.setOnClickListener {

            //salvarDados()
            //atualizarRemoverDados()
            //listarDados()
            pesquisarDados()

            //cadastroUsuario()
            //logarUsuario()
        }
    }

    private fun pesquisarDados() {

        val refUsuarios = bancoDados
            .collection("usuarios")
            //.whereEqualTo("nome", "Pedro") // Igual a pedro
            //.whereNotEqualTo("nome", "Pedro") // Que Não é igual a pedro
            //.whereIn("nome", listOf("Pedro", "Jamilton")) // Buscar que está dentro desta lista
            //.whereNotIn("nome", listOf("Pedro", "Jamilton")) // Buscar que Não esta dentro desta lista
            .whereArrayContains("conhecimentos", "kotlin") // pesquisar dado dentro de uma array

        refUsuarios.addSnapshotListener { querySnapshot, erro ->

            val listaDocuments = querySnapshot?.documents

            var listaResultado = ""
            listaDocuments?.forEach{ documentSnapshot ->
                val dados = documentSnapshot?.data
                if ( dados != null ){
                    val nome = dados["nome"]
                    val idade = dados["idade"]

                    listaResultado += "nome: $nome idade: $idade \n"
                }
            }

            binding.textResultado.text = listaResultado
        }

    }

    private fun salvarDadosUsuario(
        nome: String, idade: String
    ){
        val idUsuarioLogado = autenticacao.currentUser?.uid
        if ( idUsuarioLogado != null ){

            val dados = mapOf(
                "nome" to nome,
                "idade" to idade
                //...vários outros dados
            )

            bancoDados
                .collection("usuarios")
                .document( idUsuarioLogado )
                .set( dados )
                .addOnSuccessListener {}
                .addOnFailureListener {}
        }
    }

    private fun listarDados() {

        //salvarDadosUsuario( "Ana Maria", "25")
        val idUsuarioLogado = autenticacao.currentUser?.uid

        if ( idUsuarioLogado != null ){

            val referenciaUsuario = bancoDados
                .collection("usuarios")
                //.document( idUsuarioLogado )

            referenciaUsuario.addSnapshotListener { querySnapshot, erro ->
                val listaDocuments = querySnapshot?.documents
                var listaResultado = ""
                listaDocuments?.forEach{ documentSnapshot ->
                    val dados = documentSnapshot?.data
                    if ( dados != null ){
                        val nome = dados["nome"]
                        val idade = dados["idade"]

                        listaResultado += "nome: $nome idade: $idade \n"
                    }
                }
                binding.textResultado.text = listaResultado
            //referenciaUsuario.addSnapshotListener { documentSnapshot, erro ->

                /*val dados = documentSnapshot?.data
                if ( dados != null ){
                    val nome = dados["nome"]
                    val idade = dados["idade"]
                    val texto = "nome: $nome idade: $idade"

                    binding.textResultado.text = texto
                }*/
            }

            /*referenciaUsuario
                .get()//recuperar dados
                .addOnSuccessListener { documentSnapshot ->
                    Log.i("info_firebase", "menssagem: ${documentSnapshot.data}")
                    val dados = documentSnapshot.data
                    if ( dados != null ){
                        val nome = dados["nome"]
                        val idade = dados["idade"]
                        val texto = "nome: $nome idade: $idade"

                        binding.textResultado.text = texto
                    }
                }
                .addOnFailureListener {  }*/
        }

    }

    private fun atualizarRemoverDados() {

        val dados = mapOf(
            "nome" to "ana",
            "idade" to "25"
            //"cpf" to "12345678910"
        )

        val idUsuarioLogado = autenticacao.currentUser?.uid
        val referenciaUsuario = bancoDados
            .collection("usuarios")
            //.document("1")

        referenciaUsuario
            .add( dados )
            .addOnSuccessListener {
                exibirMensagem("Usuário adicionado com sucesso")
            }.addOnFailureListener { exception ->
                exibirMensagem("Erro ao adicionar usuário com sucesso")
            }

        //referenciaAna.set( dados )
        referenciaUsuario
            //.update("nome", "ana cristina")
            //.delete()
            .add( dados )
            .addOnSuccessListener {
                exibirMensagem("Usuário atualizado com sucesso")
            }.addOnFailureListener { exception ->
                exibirMensagem("Erro ao atualizar usuário")
            }

    }

    private fun salvarDados() {

        val dados = mapOf(
            "nome" to "ana",
            "idade" to "30",
            "cpf" to "365..."
        )

        bancoDados
            .collection("usuarios")
            .document("2")
            .set( dados )
            .addOnSuccessListener {
                exibirMensagem("Usuário salvo com sucesso")
            }.addOnFailureListener { exception ->
                exibirMensagem("Erro ao salvar usuário com sucesso")
            }

    }

    override fun onStart() {
        super.onStart()
        //verificarUsuarioLogado()
    }

    /*
    * MÉTODO DE CÓDIGO ABAIXO
    * DE UMA SIMULAÇÃO DE VERIFICAÇÃO DE USUÁRIO LOGADO ENCAMINHANDO PARA OUTRA TELA
    * */
    private fun verificarUsuarioLogado() {

        //autenticacao.signOut()
        val usuario = autenticacao.currentUser
        val id = usuario?.uid

        if( usuario != null ){
            exibirMensagem("Usuário está logado com id: $id")// Exibir um Toast de usuário logado...
            //...em seguida, Encaminhar para outra Tela (PrincipalActivity), com esse código abaixo
            startActivity(
                Intent(this, PrincipalActivity::class.java)
            )
        }else{
            exibirMensagem("Não tem usuário logado")
        }
    }

    private fun logarUsuario() {

        //Dados digitados pelo usuário
        val email = "jamilton.jm@gmail.com"
        val senha = "12345jm67@"

        //Estivesse em uma tela de Login
        autenticacao.signInWithEmailAndPassword(
            email, senha
        ).addOnSuccessListener { authResult ->
            exibirMensagem("Sucesso ao logar usuário com email $email")
            binding.textResultado.text = "Sucesso ao logar usuário"// Exibir uma mensagem de usuário logado...
            //...em seguida, Encaminhar para outra Tela (PrincipalActivity), com esse código abaixo
            startActivity(
                Intent(this, PrincipalActivity::class.java)
            )
        }.addOnFailureListener { exception ->
            binding.textResultado.text = "Falha ao logar usuário ${exception.message}"
        }
    }

    /*
    * MÉTODO DE CÓDIGO ABAIXO
    * DE UMA SIMULAÇÃO DE CADASTRO DE USUÁRIO
    * */
    private fun cadastroUsuario() {

        //Dados digitados pelo usuário
        /*val email = "jamilton.jm@gmail.com"
        val senha = "12345jm67@"
        val nome = "Jamilton Damasceno"
        val idade = "35"*/

        val email = "ana.maria@gmail.com"
        val senha = "12345jm67@"
        val nome = "Ana Maria"
        val idade = "25"

        //Tela de cadastro do seu App
        autenticacao.createUserWithEmailAndPassword(
            email, senha
        ).addOnSuccessListener { authResult ->

            val email = authResult.user?.email
            val id = authResult.user?.uid

            //Salvar mais dados do usuário no banco de dados
            salvarDadosUsuario(nome, idade)

            //exibirMensagem("Usuario Cadastrado: $id - $email")
            binding.textResultado.text = "SUCESSO!\nid: $id\nE-mail: $email\nNome: $nome"
        }.addOnFailureListener { exception ->
            val mensagemErro = exception.message
            binding.textResultado.text = "Erro: $mensagemErro"
            //exibirMensagem("Erro: $mensagemDeErro")
        }

    }

    private fun exibirMensagem(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
    }
}