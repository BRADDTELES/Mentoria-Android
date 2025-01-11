package com.danilloteles.aulaifood.data.remote.firebase.repository.autenticacao

import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.core.UIStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AutenticacaoRepositoryImpl @Inject constructor(
   private val firebaseAuth: FirebaseAuth
) : IAutenticacaoRepository {
   override suspend fun cadastrarUsuario(
      usuario: Usuario,
      uiStatus: (UIStatus<Boolean>) -> Unit
   ) {
      try {
         val retorno = firebaseAuth.createUserWithEmailAndPassword(
            usuario.email, usuario.senha
         ).await() != null

         if ( retorno ) {//true
            uiStatus.invoke(
               UIStatus.Sucesso( true )
            )
         }
      } catch ( erroUsuarioJaCadastrado: FirebaseAuthUserCollisionException ) {
         uiStatus.invoke(
            UIStatus.Erro("Usuário já cadastrado")
         )
      } catch ( erroEmailInvalido: FirebaseAuthInvalidCredentialsException ) {
         uiStatus.invoke(
            UIStatus.Erro("E-mail está inválido, digite outro e-mail!")
         )
      } catch ( erroSenhaFraca: FirebaseAuthWeakPasswordException ){
         uiStatus.invoke(
            UIStatus.Erro("Sua senha está muito fraca, digite mais caracteres!")
         )
      } catch ( erroPadrao: Exception ) {
         uiStatus.invoke(
            UIStatus.Erro("Erro ao fazer seu cadastro, tente novamente!")
         )
      }
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
      } catch ( erroUsuarioInvalido: FirebaseAuthEmailException) {
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