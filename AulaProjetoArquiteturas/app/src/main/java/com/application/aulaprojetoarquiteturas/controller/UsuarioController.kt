package com.application.aulaprojetoarquiteturas.controller

import com.application.aulaprojetoarquiteturas.model.Usuario
import com.application.aulaprojetoarquiteturas.model.api.UsuarioAPI
import com.application.aulaprojetoarquiteturas.view.MainActivity

class UsuarioController(
    private val mainActivity: MainActivity
) {

    private val usuarioAPI = UsuarioAPI()

    init {
        //recuperarUsuario()
    }

    fun recuperarUsuario() {

        //1) Regra de négocio
        val lista = usuarioAPI.recuperarUsuario()

        //2) Exibir os dados na interface
        mainActivity.listarUsuarios( lista )
    }
}