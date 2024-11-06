package com.application.aulafirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.application.aulafirebase.databinding.ActivityMainBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        binding.btnExecutar.setOnClickListener {

            //salvarDados()
            atualizarRemoverDados()

            //cadastroUsuario()
            //logarUsuario()
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

        //referenciaUsuario.set( dados )
        /*referenciaAna
            .update("nome", "ana cristina")
            .addOnSuccessListener {
                exibirMensagem("Usuário atualizado com sucesso")
            }.addOnFailureListener { exception ->
                exibirMensagem("Erro ao atualizar usuário com sucesso")
            }*/

        /*referenciaUsuario
            .delete()
            .addOnSuccessListener {
                exibirMensagem("Usuário removido com sucesso")
            }.addOnFailureListener { exception ->
                exibirMensagem("Erro ao remover usuário com sucesso")
            }*/


    }

    private fun salvarDados() {

        val dados = mapOf(
            "nome" to "ana",
            "idade" to "30",
            "cpf" to "12345678910"
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

    /*
    * MÉTODO DE CÓDIGO ABAIXO
    * DE UMA SIMULAÇÃO DE CADASTRO DE USUÁRIO
    * */
    private fun cadastroUsuario() {
        
        //Dados digitados pelo usuário
        val email = "jamilton.jm@gmail.com"
        val senha = "12345jm67@"
        
        //Tela de cadastro do seu App
        autenticacao.createUserWithEmailAndPassword(
            email, senha
        ).addOnSuccessListener { authResult ->
            val email = authResult.user?.email
            val id = authResult.user?.uid

            exibirMensagem("TOAST=Usuario Cadastrado\n$id - $email")
            binding.textResultado.text = "sucesso: $id - $email"
        }.addOnFailureListener { exception ->
            val mensagemDeErro = exception.message

            exibirMensagem("TOAST=Erro\n$mensagemDeErro")
            binding.textResultado.text = "Erro: $mensagemDeErro"
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
            binding.textResultado.text = "Suceeso ao logar usuário"// Exibir uma mensagem de usuário logado...
            //...em seguida, Encaminhar para outra Tela (PrincipalActivity), com esse código abaixo
            startActivity(
                Intent(this, PrincipalActivity::class.java)
            )
        }.addOnFailureListener { exception ->
            binding.textResultado.text = "Falha ao logar usuário ${exception.message}"
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

        if(usuario != null){
            exibirMensagem("Usuário está logado com id: $id")// Exibir um Toast de usuário logado...
            //...em seguida, Encaminhar para outra Tela (PrincipalActivity), com esse código abaixo
            startActivity(
                Intent(this, PrincipalActivity::class.java)
            )
        }else{
            exibirMensagem("Não tem usuário logado")
        }
    }

    private fun exibirMensagem(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
    }
}