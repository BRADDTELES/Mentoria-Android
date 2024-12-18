package com.application.aulacomponenteslistagemcolecoes.teste

fun main() {

    val listaNomes = listOf("jamilton", "ana", "bruno", "maria", "zeus")
    val listaNumeros = listOf(2, 1, 3)

    //val novaListaNumeros = listaNumeros.sorted()//ordena do menor para maior ASC ascendente a-z0..8
    //val novaListaNomes = listaNomes.sortedDescending()//ordena do maior para menor DESC descendente z-a8..0

    val listaNomesAsc = listaNomes.sorted()
    val listaNomesDesc = listaNomes.sortedDescending()

    println( listaNomesAsc )
    println( listaNomesDesc )

}