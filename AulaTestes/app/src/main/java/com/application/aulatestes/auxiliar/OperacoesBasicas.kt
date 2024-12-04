package com.application.aulatestes.auxiliar

class OperacoesBasicas {

   fun formataData(data: String) : String {//22102025 - 2025-10-22
      //Simular formatação de data
      return "22/10/2025"
   }

   fun exibirAlerta(mensagem: String): Boolean{
      return true
   }

   suspend fun recuperarDadosAPI(lista: List<String>): List<String> {
      return listOf(
         "jamilton", "ana", "maria", "pedro"
      )
   }

}