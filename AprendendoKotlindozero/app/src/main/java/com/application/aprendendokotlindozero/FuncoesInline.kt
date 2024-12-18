package com.application.aprendendokotlindozero

/*fun subtrair() {
    println( 10 - 8 )
}*/

/* Inline Function - Função em uma única linha sem retorno*/
//fun subtrair() = println( 10 - 8 )

/* Inline Function - Função em uma única linha com retorno*/
fun subtrair(): Int = 10 - 8

fun main() {
    //subtrair()
    val retorno = subtrair()
    println( retorno )
}