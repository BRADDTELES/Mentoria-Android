package com.danilloteles.loja.domain.model

data class Loja(
   var idLoja: String = "",
   val idCategoria: String = "",
   val categoria: String = "",
   val nome: String = "",
   val razaoSocial: String = "",
   val cnpj: String = "",
   val sobreEmpresa: String = "",
   val telefone: String = "",
   var urlPerfil: String = "",
   var urlCapa: String = "",
){
   fun toMap() : Map<String, Any> {
      val dados = mutableMapOf<String, Any>()

      if ( idLoja.isNotEmpty() ) dados["idLoja"]         = idLoja
      dados["idCategoria"]    = idCategoria
      dados["categoria"]      = categoria
      dados["nome"]           = nome
      dados["razaoSocial"]    = razaoSocial
      dados["cnpj"]           = cnpj
      dados["sobreEmpresa"]   = sobreEmpresa
      dados["telefone"]       = telefone
      if ( urlPerfil.isNotEmpty() ) dados["urlPerfil"]   = urlPerfil
      if ( urlCapa.isNotEmpty() ) dados["urlCapa"]       = urlCapa

      return dados
   }
}
