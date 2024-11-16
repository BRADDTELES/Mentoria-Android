package com.application.aulaprojetoarquiteturas.presenter

import com.application.aulaprojetoarquiteturas.model.Usuario

interface IUsuario {
    fun exibirUsuarios( lista: List<Usuario> )
}