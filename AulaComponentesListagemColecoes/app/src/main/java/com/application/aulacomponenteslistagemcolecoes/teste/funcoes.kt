package com.application.aulacomponenteslistagemcolecoes.teste

class Matematica {
    fun somar( n1: Int, n2: Int ): Int{
        return n1 + n2
    }
}

//Função
/*fun somar( n1: Int, n2: Int ): Int{
    *//* mas poderia fazer várias outras coisas *//*
    return n1 + n2
}*/

fun calcular( funcao: (Int, Int) -> Int ){

    val retorno = funcao(10 ,10)
    println( retorno )

}

fun main() {

    //calcular( ::somar )
    val matematica = Matematica()
    calcular( matematica::somar )

    /*val matematica = Matematica()
    val retorno = matematica.somar(10, 10)*/
    //val retorno = somar(10, 10)
    //println( retorno )

}