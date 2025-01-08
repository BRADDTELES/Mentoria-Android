package com.danilloteles.aulaifood.data.remote.firebase.repository

import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.core.UIStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AutenticacaoRepositoryImpl @Inject constructor(
   private val firebaseAuth: FirebaseAuth
) : IAutenticacaoRepository {
   override suspend fun cadastrarUsuario(usuario: Usuario): Boolean {
      return firebaseAuth.createUserWithEmailAndPassword(
         usuario.email, usuario.senha
      ).await() != null
   }

   override suspend fun logarUsuario(
      usuario: Usuario,
      uiStatus: (UIStatus<Boolean>) -> Unit
   ) {

      try {
         val retorno = firebaseAuth.signInWithEmailAndPassword(
            usuario.email, usuario.senha
         ).await() != null

         if ( retorno ) {//true
            uiStatus.invoke(
               UIStatus.Sucesso( true )
            )
         }
      } catch ( erroUsuarioInvalido: FirebaseAuthInvalidUserException ) {
         uiStatus.invoke(
            UIStatus.Erro("E-mail inválido, usuário não cadastrado!")
         )
      } catch ( erroSenhaInvalida: FirebaseAuthInvalidCredentialsException ) {
         uiStatus.invoke(
            UIStatus.Erro("A senha digitada está errada")
         )
      } catch ( erroPadrao: Exception ){
         uiStatus.invoke(
            UIStatus.Erro("Dados de acesso errado, tente novamente!")
         )
      }

   }

   override fun verificarUsuarioLogado(): Boolean {
      return firebaseAuth.currentUser != null
   }
}