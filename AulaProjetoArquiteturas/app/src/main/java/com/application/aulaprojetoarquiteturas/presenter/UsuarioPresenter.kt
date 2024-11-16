package com.application.aulaprojetoarquiteturas.presenter

import com.application.aulaprojetoarquiteturas.model.api.UsuarioAPI
import com.application.aulaprojetoarquiteturas.view.MainActivity

class UsuarioPresenter(
    //Alta dependência / Alto acomplamento
    private val mainActivity: MainActivity
) {

    //Injeção de dependência
    private val usuarioAPI = UsuarioAPI()

    fun recuperarUsuario() {

        //1) Regra de négocio
        val lista = usuarioAPI.recuperarUsuario()

        //2) Exibir os dados na interface
        mainActivity.exibirUsuarios( lista )
    }
}