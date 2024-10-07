package com.application.aprendendokotlindozero

// 1) valor 2) null 3) LATEINIT
class Produto {
    lateinit var descricao: String
}


fun main() {

    val produto = Produto()
    produto.descricao = "Notebook"
    println(produto.descricao)

}