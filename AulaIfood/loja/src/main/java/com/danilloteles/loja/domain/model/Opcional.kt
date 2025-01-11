package com.danilloteles.loja.domain.model

data class Opcional(
   val id: String = "",
   val idProduto: String = "",
   val nome: String = "",
   val descricao: String = "",
   val preco: Double = 0.0,
   val url: String = "",
)
