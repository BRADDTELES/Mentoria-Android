package com.application.aulacomponenteslistagemcolecoes.teste

fun main() {

    //val localizacao = Pair(10, 20) // Pair
    //val localizacao = 10 to 20 //também é um Pair
    //val localizacao = "jamilton" to "ana"
    val localizacao = Triple(10, 20, "Pizzaria") // Triple

    println( localizacao.first )
    println( localizacao.second )
    println( localizacao.third )

}