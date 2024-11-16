package com.application.aulaprojetoarquiteturas.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.application.aulaprojetoarquiteturas.R
import com.application.aulaprojetoarquiteturas.controller.UsuarioController
import com.application.aulaprojetoarquiteturas.model.Usuario

class MainActivity : AppCompatActivity() {

    // as 2 linhas abaixo podem ser utilizadas de uma ou de outra
    //private var usuarioController: UsuarioController? = null
    private lateinit var usuarioController: UsuarioController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usuarioController = UsuarioController(
            this
        )
        //usuarioController.recuperarUsuario()//Ativa

    }

    //Passiva
    fun listarUsuarios( lista: List<Usuario> ){
        //Exibir os dados
        println(lista)
        Log.i("teste", "${lista.toString()}")
    }
}