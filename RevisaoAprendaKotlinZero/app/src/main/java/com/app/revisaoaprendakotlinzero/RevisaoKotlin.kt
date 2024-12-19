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

open class Animal {

    protected var raca: String = "" //Imutavél

    open fun correr(){
        print("Correr como")
    }

}

interface Aquatico {
    fun nadar()
}

class Hipopotomo : Animal(), Aquatico {
    override fun nadar() {
        println("Nadar")
    }

}

class Passaro(
    private val cor: String
    //cor: String
) : Animal() {

    val corParamentro: String

    init {
        corParamentro = cor
    }

    public fun respirar(){}

    override fun correr(){
        super.correr()
        this.raca = "Bemtevi"
        println(" um passaro com a cor: $cor")
        //println(" um passaro com a cor: $corParamentro")
    }
}

/*fun executar( nome: String, idade: Int = 0){
    println("Nome: $nome idade: $idade")
}*/

fun main() {

    /* Classes e objetos, Herança */
    val passaro = Passaro("Marrom")
    passaro.correr()
    //passaro.cor = "Amarelo"
    //println(passaro.cor)

    /* Funções em Kotlin
    val al idade: Int = 0
    executar("Danillo")*/
    /* Arrays & Loops: while e for
    /*val totalPostagens = 2
    var contador = 0
    while ( contador <= totalPostagens ){
        println( listaPostagens[contador] )
        contador++
    }*/
    for ( postagem in listaPostagens ){
        println( postagem )
    }
    for ( indice in listaPostagens.indices ){
        println( indice )
    }
    for ( (indice, postagem) in listaPostagens.withIndex() ){
        println( "$indice - $postagem" )
    }
    for ( item in 1..10){
        println( item )
    }*/
    /* if else when
    val idade = 16 // <= 17 Adolescente - >= 18 35 - 35 ate 65

    var resultado = if (  idade >= 17  ) {
        "Adolescente"
    }else if (idade in 18..35) {// <- Range
        "Adulto"
    }else{
        "Melhor idade"
    }
    println( resultado )
    val opcao = 1
    when ( opcao ){
        1 -> {
            println("Cartão de crédito")
        }
        2 -> println("extrato")
        3 -> println("saldo")
        else -> println("Nenhuma opção escolhida")
    }*/
    /* Null Safety
    val nome: String? = null
    if ( nome != null ){
        println( nome.length )
    }
    println( nome?.length )

    var saldo: Double? = null
    //verificação de saldo
    //Recupera o saldo
    if ( saldo == null ){
        saldo = 0.0
    }
    val saldoRecuperado = saldo ?: 0.0 //Elvis opereitor
    //Exibir o saldo
    println( saldo )*/
    /* Variáveis e constantes
    //val nome = "Danillo"//Variável imutável
    var nome = "Danillo"//Variável mutável
    nome = "Maria"
    println( nome )
    println( pi )*/

}