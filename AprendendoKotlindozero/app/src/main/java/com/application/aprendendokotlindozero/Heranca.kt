package com.application.aprendendokotlindozero

abstract class Animal{//Classe pai, super classe, Abstrata
    var cor: String = ""
    var tamanho: String = ""//Pequeno, médio e grande porte
    var peso: Double = 0.0

    open fun correr(){
        print("Correr como um ")
    }

    abstract fun dormir()
}

class Cao : Animal(){//Subclasse ou classe filha, Concreta
    fun latir() = println("Latir")
    override fun correr(){
        super.correr()
        println("cão de 4 patas")
    }

    override fun dormir() {
        println("Dormir como um cão")
    }

}

class Passaro : Animal(){//Subclasse ou classe filha, Concreta
    fun voar() = println("Voar")
    override fun correr(){
        super.correr()
        println("passaro de 2 perninhas")
    }

    override fun dormir() {
        println("Dormir como um pássaro")
    }
}

fun main() {

    val cao = Cao()
    cao.correr()
    cao.latir()
    cao.dormir()

    val passaro = Passaro()
    passaro.correr()
    passaro.voar()
    passaro.dormir()

}