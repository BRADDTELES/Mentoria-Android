package com.danilloteles.aulaifood.data.remote.firebase.repository.produto

import com.danilloteles.aulaifood.domain.model.Opcional
import com.danilloteles.aulaifood.domain.model.Produto
import com.danilloteles.core.UIStatus
import com.danilloteles.core.util.ConstantesFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProdutoRepositoryImpl @Inject constructor(
   private val firebaseFirestore: FirebaseFirestore
): IProdutoRepository {

   override suspend fun listarOpcionais(
      idLoja: String,
      idProduto: String,
      uiStatus: (UIStatus<List<Opcional>>) -> Unit
   ) {
      try {

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
         uiStatus.invoke(UIStatus.Erro("Erro ao recuperar opcionais"))
      }
   }

   override suspend fun listar(
      idLoja: String,
      uiStatus: (UIStatus<List<Produto>>) -> Unit
   ) {
      try {

         val refProduto = firebaseFirestore
            .collection(ConstantesFirebase.FIRESTORE_PRODUTOS)
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
      idLoja: String,
      idProduto: String,
      uiStatus: (UIStatus<Produto>) -> Unit
   ) {
      try {

         val refProduto = firebaseFirestore
            .collection(ConstantesFirebase.FIRESTORE_PRODUTOS)
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

}