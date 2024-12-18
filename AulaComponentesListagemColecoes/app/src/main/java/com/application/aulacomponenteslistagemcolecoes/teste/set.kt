package com.application.aulacomponenteslistagemcolecoes.teste

data class Carro( val nome: String, val marca: String)

fun main() {

    //val lista = setOf("jamilton", "ana", "pedro", "ana")
    //val lista = mutableSetOf("jamilton", "ana", "pedro", "ana")
    val lista = mutableSetOf(
        Carro("Gol", "Volkswagen"),
        Carro("Brasilia", "Volkswagen")
    )

    //println( lista.size ) //contar quantos itens tenho na lista

    val novaLista = lista.plus("maria")
    novaLista.forEach { nome ->
        //println( nome )
    }
    //println( novaLista.size ) //contar quantos itens tenho na lista
    //println( novaLista.indexOf("ana") ) // retorna em qual posição o item está
    //println( novaLista.first() ) //primeiro item da lista
    //println( novaLista.last() ) //último item da lista
    //println( novaLista.contains("joão") ) //verificar se existe um item na lista em Boolean = true ou false
    val outraLista = novaLista.shuffled() // embaralhar os itens na lista
    outraLista.forEach { nome ->
        //println( nome )
    }
    //lista.add("mariana") // adicionar um item na lista
    //lista.remove("ana") // remover um item na lista
    //lista.clear() // limpar a lista
    lista.forEach { nome ->
        //println( nome )
    }
    lista.add( Carro("Astra", "Chevrolet") ) // adicionar um item na lista
    lista.forEach { carro ->
        //println( "${carro.nome} - ${carro.marca}" )
    }
    val listaEmbaralhada = lista.shuffled() // embaralhar os itens na lista
    listaEmbaralhada.forEach { carro ->
        //println( "${carro.nome} - ${carro.marca}" )
    }
    lista.remove(Carro("Gol", "Volkswagen")) // remover um item na lista
    lista.forEach { carro ->
        //println( "${carro.nome} - ${carro.marca}" )
    }
    lista.clear() // limpar a lista
    lista.forEach { carro ->
        println( "${carro.nome} - ${carro.marca}" )
    }

}