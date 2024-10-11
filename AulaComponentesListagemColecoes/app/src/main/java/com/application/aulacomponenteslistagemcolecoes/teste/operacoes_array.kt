package com.application.aulacomponenteslistagemcolecoes.teste



fun main() {

    val lista = arrayOf("danillo", "maria", "pedro", "ana")
    //println(lista.get(0))
    //println(lista[0])
    //println( lista.size ) //contar quantos itens tenho na lista
    //println( lista.indexOf("danillo") ) // retorna em qual posição o item está
    //println( lista.first() ) //primeiro item da lista
    //println( lista.last() ) //último item da lista
    //println( lista.contains("joão") ) //verificar se existe um item na lista em Boolean = true ou false
    lista.shuffle() // embaralhar os itens na lista
    //val novaLista = lista.plus("joão")
    lista.forEach { item ->
        println( item )
    }

}