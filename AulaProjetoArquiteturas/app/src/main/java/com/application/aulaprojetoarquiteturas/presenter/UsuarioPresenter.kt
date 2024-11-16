package com.application.aulaprojetoarquiteturas.presenter

import com.application.aulaprojetoarquiteturas.model.api.UsuarioAPI

class UsuarioPresenter(
    //Alta dependência / Alto acomplamento
    //private val mainActivity: MainActivity
    private val activity: IUsuario // posso usar 'iUsuario' ou 'view' ou 'activity'
) {

    //Injeção de dependência
    private val usuarioAPI = UsuarioAPI()

    fun recuperarUsuario() {

        //1) Regra de négocio
        val lista = usuarioAPI.recuperarUsuario()

        //2) Exibir os dados na interface
        activity.exibirUsuarios( lista )
    }
}