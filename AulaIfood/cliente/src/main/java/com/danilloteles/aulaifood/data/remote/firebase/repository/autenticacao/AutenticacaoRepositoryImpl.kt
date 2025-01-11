package com.danilloteles.aulaifood.data.remote.firebase.repository.autenticacao

import com.danilloteles.aulaifood.domain.model.Usuario
import com.danilloteles.core.UIStatus
import com.danilloteles.core.util.ConstantesFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AutenticacaoRepositoryImpl @Inject constructor(
   private val firebaseAuth: FirebaseAuth,
   private val firebaseFirestore: FirebaseFirestore
) : IAutenticacaoRepository {

   override suspend fun recuperarDadosUsuarioLogado(
      uiStatus: (UIStatus<Usuario>) -> Unit
   ) {
      try {

         val idUsuario = firebaseAuth.currentUser?.uid ?:
            return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         val refUsuario = firebaseFirestore
            .collection(ConstantesFirebase.FIRESTORE_USUARIOS)
            .document( idUsuario )

         val documentSnapshot = refUsuario.get().await()
         if ( documentSnapshot.exists() ) {
            val usuario = documentSnapshot.toObject( Usuario::class.java )
            if (usuario != null) {
               uiStatus.invoke(UIStatus.Sucesso( usuario ))
            }else{
               uiStatus.invoke(UIStatus.Erro("Erro ao converter dados da usuário"))
            }
         }else{
            uiStatus.invoke(UIStatus.Erro("Não existem dados do usuário"))
         }

      } catch (erroRecuperarLoja: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao recuperar dados do usuário"))
      }
   }

   override suspend fun atualizarUsuario(
      usuario: Usuario,
      uiStatus: (UIStatus<String>) -> Unit
   ) {
      try {
         val idUsuario = firebaseAuth.currentUser?.uid ?:
            return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         val refUsuario = firebaseFirestore
            .collection( ConstantesFirebase.FIRESTORE_USUARIOS )
            .document( idUsuario )

         refUsuario.update( usuario.mapToUsuarioFirestore() ).await()//Atualizar

         uiStatus.invoke( UIStatus.Sucesso( idUsuario ) )

      } catch (erroAtualizarCampo: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao atualizar dados do usuario"))
      }
   }

   override suspend fun cadastrarUsuario(
      usuario: Usuario,
      uiStatus: (UIStatus<Boolean>) -> Unit
   ) {
      try {

         firebaseAuth.createUserWithEmailAndPassword(
            usuario.email, usuario.senha
         ).await()

         val idUsuario = firebaseAuth.currentUser?.uid ?:
            return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         //Salvar dados usuario no Firestore
         usuario.id = idUsuario
         val usuarioRef = firebaseFirestore
            .collection( ConstantesFirebase.FIRESTORE_USUARIOS )
            .document( idUsuario )

         usuarioRef.set(
            usuario.mapToUsuarioFirestore()
         ).await()

         uiStatus.invoke(
            UIStatus.Sucesso( true )
         )

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

   override fun recuperarIdUsuarioLogado(): String {
      return firebaseAuth.currentUser?.uid ?: ""
   }

   override fun deslogarUsuario() {
      firebaseAuth.signOut()
   }
}