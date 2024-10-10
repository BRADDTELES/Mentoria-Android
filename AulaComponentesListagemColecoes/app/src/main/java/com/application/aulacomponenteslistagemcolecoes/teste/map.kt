package com.application.aulacomponenteslistagemcolecoes.teste

data class Produto( val nome: String )

fun main() {

    //val lista = mapOf(102545 to "Notebook", 105632 to "Iphone")
    //val lista = mutableMapOf("102545" to Produto("Notebook"), "105632" to Produto("Iphone")) //Obtendo dados da classe
    val lista = mutableMapOf("nome" to "Notebook", "preço" to 230)
    //println( lista["nome"] )
    //println( lista.get("preço") )
    //println( lista.size ) //contar quantos itens tenho na lista
    //println( lista.contains("102545") ) //verificar se existe um item na lista em Boolean = true ou false
    //lista.put("idade", 20) // adicionar um item na lista
    lista["idade"] = 20 //adicionar
    lista.put("nome", "Iphone") //atualizar um item na lista
    lista.put("preço", 400) //atualizar um item na lista
    lista.remove("idade") // remover um item na lista
    lista.clear() // limpar a lista
    lista.forEach { produto ->
        //println("${produto.key} - ${produto.value.nome}") // Mostrando dados da classe
        println("${produto.key} - ${produto.value}")
    }

}