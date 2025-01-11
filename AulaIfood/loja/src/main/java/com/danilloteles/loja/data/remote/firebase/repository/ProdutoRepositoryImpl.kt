package com.danilloteles.loja.data.remote.firebase.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.danilloteles.loja.domain.model.Produto
import com.danilloteles.core.UIStatus
import com.danilloteles.loja.util.Constantes
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
         refProduto.set( produto ).await()//Salvar produto

         uiStatus.invoke( UIStatus.Sucesso(idProduto) )

      } catch (erroAtualizarCampo: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao atualizar dados do produto"))
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

         refProduto.update( produto.toMap() ).await()//Atualizar produto

         uiStatus.invoke( UIStatus.Sucesso( produto.id ) )

      } catch (erroAtualizarCampo: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao atualizar dados do produto"))
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
            uiStatus.invoke(UIStatus.Sucesso(emptyList()))
         }
      } catch (erroRecuperarLoja: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao recuperar produtos"))
      }
   }

   override suspend fun recuperarProdutoPeloId(
      idProduto: String,
      uiStatus: (UIStatus<Produto>) -> Unit
   ) {
      try {

         val idLoja = firebaseAuth.currentUser?.uid ?:
            return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         val refProduto = firebaseFirestore
            .collection(Constantes.FIRESTORE_PRODUTOS)
            .document( idLoja )
            .collection("itens")
            .document( idProduto )

         val documentSnapshot = refProduto.get().await()
         if ( documentSnapshot.exists() ) {
            val produto = documentSnapshot.toObject( Produto::class.java )
            if (produto != null) {
               uiStatus.invoke(UIStatus.Sucesso(produto))
            }else{
               uiStatus.invoke(UIStatus.Erro("Erro ao converter dados do produto"))
            }
         }else{
            uiStatus.invoke(UIStatus.Erro("Não existem dados para o produto"))
         }
      } catch (erroRecuperarLoja: Exception) {
         uiStatus.invoke(UIStatus.Erro("Erro ao recuperar dados do produto"))
      }
   }

   override suspend fun remover(
      idProduto: String,
      uiStatus: (UIStatus<Boolean>) -> Unit
   ) {
      try {

         val idLoja = firebaseAuth.currentUser?.uid ?:
            return uiStatus.invoke( UIStatus.Erro("Usuário não está logado") )

         val refProduto = firebaseFirestore
            .collection(Constantes.FIRESTORE_PRODUTOS)
            .document( idLoja )
            .collection("itens")
            .document( idProduto )

         refProduto.delete().await()//Remover produto
         uiStatus.invoke( UIStatus.Sucesso(true) )

      } catch ( erroAtualizarCampo: Exception ) {
         Log.e(TAG, "MENSSAGEM DE ERRO:\n---> ${erroAtualizarCampo.message}")//Log para visualizar o Erro
         /*
         MENSSAGEM DE ERRO:
         ---> The specified child already has a parent.
         You must call removeView() on the child's parent first.
         * */
         uiStatus.invoke(UIStatus.Erro("Erro ao remover produto"))
      }
   }


}