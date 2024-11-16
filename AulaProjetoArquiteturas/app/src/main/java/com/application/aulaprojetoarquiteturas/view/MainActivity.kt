package com.application.aulaprojetoarquiteturas.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.application.aulaprojetoarquiteturas.R
import com.application.aulaprojetoarquiteturas.controller.UsuarioController
import com.application.aulaprojetoarquiteturas.databinding.ActivityMainBinding
import com.application.aulaprojetoarquiteturas.model.Usuario

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    // as 2 linhas abaixo podem ser utilizadas de uma ou de outra
    //private var usuarioController: UsuarioController? = null
    private lateinit var usuarioController: UsuarioController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        usuarioController = UsuarioController(
            this
        )
        //usuarioController.recuperarUsuario()//Ativa
        binding.btnRecuperar.setOnClickListener {
            usuarioController.recuperarUsuario()
        }

    }

    //Passiva
    fun listarUsuarios( lista: List<Usuario> ){
        //Log.i("teste", "${lista.toString()}")

        var textoUsuarios = ""
        lista.forEach {  usuario ->
            textoUsuarios += "${usuario.nome} - ${usuario.idade} \n"
        }
        binding.textResultado.text = textoUsuarios

    }
}