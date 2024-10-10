package com.application.aulacomponenteslistagemcolecoes.teste

data class Cliente( val nome: String, val idade: Int ) {

}

fun main() {

    //val cliente1 = Cliente("Jamilton", 35)
    //val cliente2 = Cliente("Ana", 22)

    val lista = mutableListOf("jamilton", "ana", "maria", "pedro")
    /*val listaClientes = mutableListOf(
        Cliente("Jamilton", 35),
        Cliente("Ana", 22)
    )*/

    /*listaArray[0] = "Jamilton Alterado"

    val novaLista = listOf("marcelo", "marcela")
    listaArray.addAll( novaLista )
    //listaArray.add("márcia")
    listaArray.removeAt(3)*/

    //listaClientes.clear()
    //listaClientes.size

    //println( lista[1] )
    val novaLista = lista.shuffled()
    novaLista.forEach { nome ->
        println( nome )
        //println("${cliente.nome} - ${cliente.idade}")
    }

}