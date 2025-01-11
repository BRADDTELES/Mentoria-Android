package com.danilloteles.loja.data.remote.firebase.repository

import com.danilloteles.core.UIStatus
import com.danilloteles.loja.domain.model.Opcional
import com.danilloteles.loja.util.Constantes
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
            .collection(Constantes.FIRESTORE_PRODUTOS)
            .document( idLoja )
            .collection(Constantes.FIRESTORE_ITENS)
            .document( opcional.idProduto )
            .collection(Constantes.FIRESTORE_OPCIONAIS)
            .document()

         val idOpcional = refOpcional.id
         opcional.id = idOpcional
         refOpcional.set( opcional ).await()//Salvar

         uiStatus.invoke( UIStatus.Sucesso(idOpcional) )

      } catch (erroAtualizarCampo: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao atualizar dados do opcional"))
      }
   }

   override suspend fun listar(uiStatus: (UIStatus<List<Opcional>>) -> Unit) {

   }

   override suspend fun remover(
      opcional: Opcional, uiStatus: (UIStatus<Boolean>) -> Unit
   ) {

   }
}