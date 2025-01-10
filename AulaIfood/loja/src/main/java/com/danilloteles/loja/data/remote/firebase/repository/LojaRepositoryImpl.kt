package com.danilloteles.loja.data.remote.firebase.repository

import com.danilloteles.core.UIStatus
import com.danilloteles.loja.util.Constantes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LojaRepositoryImpl @Inject constructor(
   private val firebaseAuth: FirebaseAuth,
   private val firebaseFirestore: FirebaseFirestore
) : ILojaRepository {
   override suspend fun atualizarCampo(
      campo: Map<String, Any>,
      uiStatus: (UIStatus<Boolean>) -> Unit
   ) {

      try {

         val idLoja = firebaseAuth.currentUser?.uid ?:
            return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         val refLoja = firebaseFirestore
            .collection(Constantes.FIRESTORE_LOJAS)
            .document( idLoja )
         refLoja.update( campo ).await()

         uiStatus.invoke( UIStatus.Sucesso(true) )

      } catch (erroAtualizarCampo: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao atualizar dados"))
      }

   }
}