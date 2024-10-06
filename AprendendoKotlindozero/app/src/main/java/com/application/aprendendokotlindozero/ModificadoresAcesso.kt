package com.application.aprendendokotlindozero

open class Carro {

    //var modelo = ""

    protected open fun injetarCombustivel(){
        println("Injeção do combustível")
    }

    fun acelerar(){
        injetarCombustivel()
        println("Acelerar o carro")
    }

}

class Gol : Carro(){
    override fun injetarCombustivel() {
        super.injetarCombustivel()
    }
}

class Ferrari : Carro(){

}

fun main() {

    val gol = Gol()
    gol.acelerar()

    /*val carro = Carro()
    carro.modelo = "teste"
    carro.acelerar()*/

}