package com.application.aulaprojetoarquiteturas.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulaprojetoarquiteturas.databinding.ActivityMainBinding
import com.application.aulaprojetoarquiteturas.model.Usuario
import com.application.aulaprojetoarquiteturas.presenter.IUsuario
import com.application.aulaprojetoarquiteturas.presenter.UsuarioPresenter

class MainActivity : AppCompatActivity(), IUsuario {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    // as 2 linhas abaixo podem ser utilizadas de uma ou de outra
    //private var usuarioController: UsuarioController? = null
    //private lateinit var usuarioController: UsuarioController
    private var usuarioPresenter: UsuarioPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        /*usuarioController = UsuarioController(
            this
        )
        //usuarioController.recuperarUsuario()//Ativa
        binding.btnRecuperar.setOnClickListener {
            usuarioController.recuperarUsuario()
        }*/

        usuarioPresenter = UsuarioPresenter(this)
        binding.btnRecuperar.setOnClickListener {
            usuarioPresenter?.recuperarUsuario()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        usuarioPresenter = null
    }

    //Passiva
    override fun exibirUsuarios( lista: List<Usuario> ){
        //Log.i("teste", "${lista.toString()}")

        var textoUsuarios = ""
        lista.forEach {  usuario ->
            textoUsuarios += "${usuario.nome} - ${usuario.idade} \n"
        }
        binding.textResultado.text = textoUsuarios

    }
}