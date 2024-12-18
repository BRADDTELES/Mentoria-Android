package com.application.projetoturmafirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.application.projetoturmafirebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            binding.btnCadastrar.setOnClickListener {
                cadastrarUsuario()
            }
            binding.btnLogar.setOnClickListener {
                logarUsuario()
            }
        }

    }

    // onStart geralmente é o método para Recuperar os dados, ex.: dados de uma API, dados do banco de dados, etc
    override fun onStart() {
        super.onStart()
        verificarUsuarioLogado()
    }

    private fun verificarUsuarioLogado() {

        val usuarioAtual = autenticacao.currentUser
        if ( usuarioAtual != null ){
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            Log.i("info_firebase", "Usuario logado: ${usuarioAtual.email}")
        }


    }

    private fun logarUsuario() {

        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        val retornoValidacao = validarUsuario(email, senha)
        if ( retornoValidacao ){

            autenticacao.signInWithEmailAndPassword(
                email, senha
            ).addOnSuccessListener {
                exibirMensagem("Sucesso ao logar de usuário")

                startActivity(
                    Intent(this, MainActivity::class.java)
                )
            }.addOnFailureListener {  erro ->
                erro.printStackTrace()
                exibirMensagem("Erro ao logar de usuário")
            }
        }



    }

    private fun cadastrarUsuario() {

        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        val retornoValidacao = validarUsuario(email, senha)
        if (retornoValidacao){
            autenticacao.createUserWithEmailAndPassword(
                email, senha
            ).addOnSuccessListener { 
                exibirMensagem("Sucesso ao fazer cadastro de usuário")

                startActivity(
                    Intent(this, MainActivity::class.java)
                )
            }.addOnFailureListener {  erro ->
                erro.printStackTrace()
                exibirMensagem("Erro ao faazer cadastro de usuário")
            }
        }


    }

    private fun validarUsuario(email: String, senha: String) : Boolean { //true -> Validado false -> não validado
        if ( email.isNotEmpty() ){
            if ( senha.isNotEmpty() ){
                return true
            }
        }
        return false
    }
    
    private fun exibirMensagem(mensagem: String){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}