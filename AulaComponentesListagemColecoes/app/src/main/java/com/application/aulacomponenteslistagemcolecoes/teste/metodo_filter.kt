package com.application.aulacomponenteslistagemcolecoes.teste

fun main() {

    //val listaFrutas = listOf("maçã", "laranja", "banana")
    //val listaVendas = listOf(100, 500, 150, 1500, 800, 600)
    val listaNomes = listOf("jamilton", "pedro", "ana")

    /*val novaLista = listaFrutas.filter { fruta ->
        fruta == "laranja" // se não existe a fruta na lista, retornará só colchetes []
    }*/
    /*val novaLista = listaVendas.filter { valor ->
        valor >= 600 // condição (true ou false), se for false retornará só colchetes []
    }*/
    /*val novaLista = listaVendas.filter { it >= 500 }*/
    val novaLista = listaNomes.filter { nome ->
        nome.contains("a")// true ou false
    }
    println( novaLista )

}