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
)
