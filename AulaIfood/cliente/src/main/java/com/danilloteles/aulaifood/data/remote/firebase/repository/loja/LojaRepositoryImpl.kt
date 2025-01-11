package com.danilloteles.aulaifood.data.remote.firebase.repository.loja

import com.danilloteles.aulaifood.domain.model.Loja
import com.danilloteles.core.UIStatus
import com.danilloteles.core.util.ConstantesFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LojaRepositoryImpl @Inject constructor(
   private val firebaseFirestore: FirebaseFirestore
) : ILojaRepository {

   override suspend fun recuperarDadosLoja(
      idLoja: String,
      uiStatus: (UIStatus<Loja>) -> Unit
   ) {
      try {

         val refLoja = firebaseFirestore
            .collection(ConstantesFirebase.FIRESTORE_LOJAS)
            .document( idLoja )

         val documentSnapshot = refLoja.get().await()
         if ( documentSnapshot.exists() ) {
            val loja = documentSnapshot.toObject( Loja::class.java )
            if (loja != null) {
               uiStatus.invoke(UIStatus.Sucesso( loja ))
            }else{
               uiStatus.invoke(UIStatus.Erro("Erro ao converter dados da loja"))
            }
         }else{
            uiStatus.invoke(UIStatus.Erro("Não existem dados para a loja"))
         }

      } catch (erroRecuperarLoja: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao recuperar dados da loja"))
      }
   }

   override suspend fun listar(
      uiStatus: (UIStatus<List<Loja>>) -> Unit
   ) {
      try {

         val refLojas = firebaseFirestore
            .collection(ConstantesFirebase.FIRESTORE_LOJAS)

         val querySnapshot = refLojas.get().await()
         if ( querySnapshot.documents.isNotEmpty()) {
            val lojas = querySnapshot.documents.mapNotNull { documentSnapshot ->
               documentSnapshot.toObject( Loja::class.java )
            }
            uiStatus.invoke(UIStatus.Sucesso( lojas ))
         }else{
            uiStatus.invoke(UIStatus.Sucesso( emptyList() ))
         }

      } catch (erroRecuperarLoja: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao recuperar lojas"))
      }
   }
}