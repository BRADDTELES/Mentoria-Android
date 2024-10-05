package com.application.aprendendokotlindozero

fun main() {

    /*val notaAluno = 7
    val condicao = notaAluno >= 6

    val opcao = 10
    if ( opcao == 1 ){
        println("Cartão de crédito")
    }else if ( opcao == 2 ){
        println("Extrato bancário")
    }else if ( opcao == 3 ){
        println("Saldo")
    }else{
        println("Nenhuma opção selecionada")
    }


    val opcao = 6
    if ( opcao in 1..3){
        println("opçoes selecionaddas: 1, 2, 3")
    }else if ( opcao in 4..8 ){
        println("opçoes selecionaddas: 4 até 8")
    }
    */

    /* when é parecido com switch case */

    val opcao = 4
    when ( opcao ) {
        1 -> println("Cartão de crédito")
        2 -> println("Extrato bancário")
        in 3..5 -> println("Saldo") // in se está dentro do intervalo de 3 até 5
        else -> println("Nenhuma opção selecionada")
    }
}