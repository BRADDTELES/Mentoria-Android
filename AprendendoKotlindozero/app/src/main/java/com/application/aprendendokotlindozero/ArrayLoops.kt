package com.application.aprendendokotlindozero

fun main() {

    /*
    val nomes = arrayOf(
        "Danillo", "Ana"
    )
    nomes[0] = "mudou" //substitui o valor 'Danillo' para 'mudou'

    println( nomes[0] )
    */
    //var numero = 1
    /*while ( numero <= 5 ){
        println("Executou: $numero")
        numero++
    }*/
    /*while ( numero in 1..5 ){
        println("Executou: $numero")
        numero++
    }*/

    /*for ( numero in 3..10 ){
        println("Executou: $numero")
    }*/

    val postagens = arrayOf(
        "Vaigem para a praia",
        "Levei meu cachorro no veterinário",
        "Trilha com os amigos"
    )
    for ( (indice, postagem) in postagens.withIndex() ){
        println("titulo[$indice]: $postagem")
    }

}