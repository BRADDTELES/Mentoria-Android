package com.danilloteles.loja.domain.model

data class Produto(
   var id: String = "",
   val nome: String = "",
   val descricao: String = "",
   val preco: Double = 0.0,
   val precoDestaque: Double = 0.0,
   val url: String = "",
   val emDestaque: Boolean = false
){
   fun toMap() : Map<String, Any> {
      val dados = mutableMapOf<String, Any>()

      if ( id.isNotEmpty() )                       dados["id"] = id
      if ( nome.isNotEmpty() )                     dados["nome"] = nome
      if ( descricao.isNotEmpty() )                dados["descricao"] = descricao
      if ( preco.toString().isNotEmpty() )         dados["preco"] = preco
      if ( precoDestaque.toString().isNotEmpty() ) dados["precoDestaque"] = precoDestaque
      if ( url.isNotEmpty() )                      dados["url"] = url
      dados["emDestaque"] = emDestaque

      return dados
   }
}
