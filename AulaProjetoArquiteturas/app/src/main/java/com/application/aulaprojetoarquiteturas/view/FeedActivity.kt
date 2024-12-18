package com.application.aulaprojetoarquiteturas.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.application.aulaprojetoarquiteturas.databinding.ActivityFeedBinding
import com.application.aulaprojetoarquiteturas.model.Usuario
import com.application.aulaprojetoarquiteturas.presenter.IUsuario
import com.application.aulaprojetoarquiteturas.presenter.UsuarioPresenter

class FeedActivity : AppCompatActivity(), IUsuario {

    private val binding by lazy {
        ActivityFeedBinding.inflate(layoutInflater)
    }
    private lateinit var usuarioPresenter: UsuarioPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        usuarioPresenter = UsuarioPresenter(this)

    }

    override fun onStart() {
        super.onStart()
        usuarioPresenter.recuperarUsuarios()
    }

    override fun exibirUsuarios( lista: List<Usuario> ){
        //Log.i("teste", "${lista.toString()}")

        var textoUsuarios = ""
        lista.forEach {  usuario ->
            textoUsuarios += "${usuario.nome} - ${usuario.idade} \n"
        }
        Log.i("exibir_presenter", "${textoUsuarios}")

    }
}