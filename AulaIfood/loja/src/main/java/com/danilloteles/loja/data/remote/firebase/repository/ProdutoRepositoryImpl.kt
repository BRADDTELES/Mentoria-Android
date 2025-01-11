package com.danilloteles.loja.data.remote.firebase.repository

import com.danilloteles.loja.domain.model.Produto
import com.danilloteles.core.UIStatus
import com.danilloteles.loja.domain.model.Categoria
import com.danilloteles.loja.util.Constantes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProdutoRepositoryImpl @Inject constructor(
   private val firebaseAuth: FirebaseAuth,
   private val firebaseFirestore: FirebaseFirestore
): IProdutoRepository {
   override suspend fun salvar(
      produto: Produto,
      uiStatus: (UIStatus<String>) -> Unit
   ) {
      try {
         val idLoja = firebaseAuth.currentUser?.uid ?:
            return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         val refProduto = firebaseFirestore
            .collection(Constantes.FIRESTORE_PRODUTOS)
            .document( idLoja )
            .collection("itens")
            .document()

         val idProduto = refProduto.id
         produto.id = idProduto

         refProduto.set( produto ).await()

         uiStatus.invoke( UIStatus.Sucesso(idProduto) )

      } catch (erroAtualizarCampo: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao atualizar dados de produto"))
      }
   }

   override suspend fun atualizar(
      produto: Produto,
      uiStatus: (UIStatus<String>) -> Unit
   ) {
      try {
         val idLoja = firebaseAuth.currentUser?.uid ?:
            return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         val refProduto = firebaseFirestore
            .collection(Constantes.FIRESTORE_PRODUTOS)
            .document( idLoja )
            .collection("itens")
            .document( produto.id )

         refProduto.update( produto.toMap() ).await()

         uiStatus.invoke( UIStatus.Sucesso( produto.id ) )

      } catch (erroAtualizarCampo: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao atualizar dados de produto"))
      }
   }

   override suspend fun listar(
      uiStatus: (UIStatus<List<Produto>>) -> Unit
   ) {
      try {

         val idLoja = firebaseAuth.currentUser?.uid ?:
            return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         val refProduto = firebaseFirestore
            .collection(Constantes.FIRESTORE_PRODUTOS)
            .document( idLoja )
            .collection("itens")

         val querySnapshot = refProduto.get().await()

         if ( querySnapshot.documents.isNotEmpty()) {
            val listaProdutos = querySnapshot.documents.mapNotNull { documentSnapshot ->
               documentSnapshot.toObject( Produto::class.java )
            }
            uiStatus.invoke(UIStatus.Sucesso( listaProdutos ))
         }else{
            uiStatus.invoke(UIStatus.Sucesso( emptyList() ))
         }

      } catch (erroRecuperarLoja: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao recuperar produtos"))
      }
   }
}