package com.danilloteles.loja.data.remote.firebase.repository

import com.danilloteles.core.UIStatus
import com.danilloteles.loja.domain.model.Categoria
import com.danilloteles.loja.domain.model.Loja
import com.danilloteles.loja.util.Constantes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
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

   override suspend fun recuperarDadosLoja(
      uiStatus: (UIStatus<Loja>) -> Unit
   ) {
      try {
         val idLoja = firebaseAuth.currentUser?.uid ?:
            return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         val refLoja = firebaseFirestore
            .collection(Constantes.FIRESTORE_LOJAS)
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

   override suspend fun recuperarCategorias(
      uiStatus: (UIStatus<List<Categoria>>) -> Unit
   ) {
      try {

         val refCategorias = firebaseFirestore
            .collection(Constantes.FIRESTORE_CATEGORIAS)

         val querySnapshot = refCategorias.get().await()

         if ( querySnapshot.documents.isNotEmpty()) {
            val listaCategorias = querySnapshot.documents.mapNotNull { documentSnapshot ->
               documentSnapshot.toObject( Categoria::class.java )
            }
            uiStatus.invoke(UIStatus.Sucesso( listaCategorias ))
         }else{
            uiStatus.invoke(UIStatus.Sucesso( emptyList() ))
         }

      } catch (erroRecuperarLoja: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao recuperar categorias"))
      }
   }
}