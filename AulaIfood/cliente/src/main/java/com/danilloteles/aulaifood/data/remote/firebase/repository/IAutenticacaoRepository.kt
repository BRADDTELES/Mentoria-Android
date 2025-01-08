package com.danilloteles.aulaifood.data.remote.firebase.repository

import com.danilloteles.aulaifood.domain.model.Usuario

interface IAutenticacaoRepository {
   suspend fun cadastrarUsuario( usuario: Usuario ) : Boolean
   suspend fun logarUsuario( usuario: Usuario ) : Boolean
   fun verificarUsuarioLogado() : Boolean
}