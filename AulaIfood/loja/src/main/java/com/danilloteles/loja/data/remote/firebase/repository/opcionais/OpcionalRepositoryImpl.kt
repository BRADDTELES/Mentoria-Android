package com.danilloteles.loja.data.remote.firebase.repository.opcionais

import android.content.ContentValues.TAG
import android.util.Log
import com.danilloteles.core.UIStatus
import com.danilloteles.loja.domain.model.Opcional
import com.danilloteles.core.util.ConstantesFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OpcionalRepositoryImpl@Inject constructor(
   private val firebaseAuth: FirebaseAuth,
   private val firebaseFirestore: FirebaseFirestore
): IOpcionalRepository {

   /*
   * produtos
   *  + idLoja
   *     + itens
   *        idProduto
   *          + opcionais
   *              idOpcional
   * */
   override suspend fun salvar(
      opcional: Opcional,
      uiStatus: (UIStatus<String>) -> Unit
   ) {
      try {

         val idLoja = firebaseAuth.currentUser?.uid ?:
            return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         val refOpcional = firebaseFirestore
            .collection(ConstantesFirebase.FIRESTORE_PRODUTOS)
            .document( idLoja )
            .collection(ConstantesFirebase.FIRESTORE_ITENS)
            .document( opcional.idProduto )
            .collection(ConstantesFirebase.FIRESTORE_OPCIONAIS)
            .document()

         val idOpcional = refOpcional.id
         opcional.id = idOpcional
         refOpcional.set( opcional ).await()//Salvar

         uiStatus.invoke( UIStatus.Sucesso(idOpcional) )

      } catch (erroAtualizarCampo: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao atualizar dados do opcional"))
      }
   }

   override suspend fun listar(
      idProduto: String,
      uiStatus: (UIStatus<List<Opcional>>) -> Unit
   ) {
      try {

         val idLoja = firebaseAuth.currentUser?.uid ?:
            return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         val refOpcional = firebaseFirestore
            .collection(ConstantesFirebase.FIRESTORE_PRODUTOS)
            .document( idLoja )
            .collection(ConstantesFirebase.FIRESTORE_ITENS)
            .document( idProduto )
            .collection(ConstantesFirebase.FIRESTORE_OPCIONAIS)

         val querySnapshot = refOpcional.get().await()
         if ( querySnapshot.documents.isNotEmpty()) {
            val listaOpcionais = querySnapshot.documents.mapNotNull { documentSnapshot ->
               documentSnapshot.toObject( Opcional::class.java )
            }
            uiStatus.invoke(UIStatus.Sucesso( listaOpcionais ))
         }else{
            uiStatus.invoke(UIStatus.Sucesso(emptyList()))
         }
      } catch (erroRecuperarLoja: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao recuperar produtos"))
      }
   }

   override suspend fun remover(
      opcional: Opcional,
      uiStatus: (UIStatus<Boolean>) -> Unit
   ) {
      try {

         val idLoja = firebaseAuth.currentUser?.uid ?:
         return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         val refOpcional = firebaseFirestore
            .collection(ConstantesFirebase.FIRESTORE_PRODUTOS)
            .document( idLoja )
            .collection(ConstantesFirebase.FIRESTORE_ITENS)
            .document( opcional.idProduto )
            .collection(ConstantesFirebase.FIRESTORE_OPCIONAIS)
            .document( opcional.id )

         refOpcional.delete().await()//Remover produto
         uiStatus.invoke( UIStatus.Sucesso(true) )

      } catch ( erroAtualizarCampo: Exception ) {
         Log.e(TAG, "MENSSAGEM DE ERRO:\n---> ${erroAtualizarCampo.message}")//Log para visualizar o Erro
         /*
         MENSSAGEM DE ERRO:
         ---> The specified child already has a parent.
         You must call removeView() on the child's parent first.
         * */
         uiStatus.invoke(UIStatus.Erro("Erro ao remover opcional"))
      }
   }
}