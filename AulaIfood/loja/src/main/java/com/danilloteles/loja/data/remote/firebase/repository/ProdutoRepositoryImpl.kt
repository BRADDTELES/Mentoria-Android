package com.danilloteles.loja.data.remote.firebase.repository

import com.danilloteles.aulaifood.domain.model.Produto
import com.danilloteles.core.UIStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class ProdutoRepositoryImpl @Inject constructor(
   private val firebaseAuth: FirebaseAuth,
   private val firebaseFirestore: FirebaseFirestore
): IProdutoRepository {
   override suspend fun salvar(produto: Produto, uiStatus: (UIStatus<String>) -> Unit) {

   }

   override suspend fun atualizar(produto: Produto, uiStatus: (UIStatus<String>) -> Unit) {

   }
}