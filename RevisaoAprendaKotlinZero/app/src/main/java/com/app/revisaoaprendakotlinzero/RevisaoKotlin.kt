package com.app.revisaoaprendakotlinzero

/*
Resumão Aprenda Kotlin do zero
+ Variáveis e constantes
+ Null Safety - chamada segura (?)
+ Operadores aritiméticos, relacionais e lógicos
+ if else when
+ Arrays & Loops: while e for
+ Funções e parâmetros
+ Classes, objetos, atributos e métodos
+ Sobrecarga métodos
+ Modificadores de acesso (public, private, protected)
+ Herança
+ Interface
+ Companion Object
+ Lateinit
* */

const val pi = 3.14

fun main() {

    //val nome = "Danillo"//Variável imutável
    /*var nome = "Danillo"//Variável mutável
    nome = "Maria"
    println( nome )
    println( pi )*/

    /*val nome: String? = null
    if ( nome != null ){
        println( nome.length )
    }
    println( nome?.length )*/

    var saldo: Double? = null

    //verificação de saldo
    //Recupera o saldo
    if ( saldo == null ){
        saldo = 0.0
    }
    val saldoRecuperado = saldo ?: 0.0 //Elvis opereitor

    //Exibir o saldo
    println( saldo )

}