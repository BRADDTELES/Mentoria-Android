package com.application.aulaprojetoarquiteturas

import android.util.Log
import com.application.aulaprojetoarquiteturas.model.Usuario
import com.application.aulaprojetoarquiteturas.presenter.IUsuario
import com.application.aulaprojetoarquiteturas.presenter.UsuarioPresenter
import com.application.aulaprojetoarquiteturas.viewmodel.UsuarioViewModel

class UsuarioTest {

    inner class SimularActivity : IUsuario{
        override fun exibirUsuarios(lista: List<Usuario>) {
            Log.i("controller_test", "${lista.toString()}")
        }
    }

    fun testarRecuperarUsuarios(){

        /*//Entrada
        //Processamento
        val usuarioController = UsuarioController(this)
        usuarioController.recuperarUsuario()
        //Saida*/

        //Facilitar os testes
        val simularActivity = SimularActivity()
        val usuarioPresenter = UsuarioPresenter( simularActivity )
        usuarioPresenter.recuperarUsuarios()

        //Simular testes com MVVM
        val usuarioViewModel = UsuarioViewModel()
        usuarioViewModel.recuperarUsuarios()

    }

}