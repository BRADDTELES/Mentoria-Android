package com.application.aulaprojetoarquiteturas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.aulaprojetoarquiteturas.model.Usuario
import com.application.aulaprojetoarquiteturas.model.api.UsuarioAPI

class UsuarioViewModel : ViewModel() {//Passivo

    private val usuarioAPI = UsuarioAPI()
    val usuariosLiveData = MutableLiveData< List<Usuario> >()

    fun recuperarUsuarios() {
        val lista = usuarioAPI.recuperarUsuario()
        usuariosLiveData.value = lista
    }

}