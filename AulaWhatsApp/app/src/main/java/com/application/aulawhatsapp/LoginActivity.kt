package com.application.aulawhatsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulawhatsapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var nome: String
    private lateinit var email: String
    private lateinit var senha: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inicializarEventosClique()

    }

    private fun inicializarEventosClique() {

        binding.textCadastro.setOnClickListener {
            startActivity(
                Intent(this, CadastroActivity::class.java)
            )
        }

        binding.btnLogar.setOnClickListener {
            if (validarCampos()){
                startActivity(
                    Intent(applicationContext, MainActivity::class.java)
                )
            }
        }

    }

    private fun validarCampos(): Boolean {
        email = binding.editEmail.text.toString()
        senha = binding.editSenha.text.toString()


        if (email.isNotEmpty()){
            binding.textInputLayoutEmail.error = null
            if (senha.isNotEmpty()){
                binding.textInputLayoutSenha.error = null
                return true
            }else{
                binding.textInputLayoutSenha.error = "Preencha a senha!"
                return false
            }
        }else{
            binding.textInputLayoutEmail.error = "Preencha o seu e-mail!"
            return false
        }
    }
}