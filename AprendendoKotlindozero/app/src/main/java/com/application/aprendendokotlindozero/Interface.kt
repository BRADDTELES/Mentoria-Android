package com.application.aprendendokotlindozero

interface Presidenciavel {
    fun candidatarSe()
}

abstract class Pessoa{
    fun comer() = println("comer")
    //abstract fun candidatarSe()
}

class DesenvolvedorAndroid : Pessoa(), Presidenciavel{
    fun programar() = println("programar")
    override fun candidatarSe() {
        println("Fazer o processo para se candidatar")
    }
}

class DesenvolvedorWeb : Pessoa(){
    fun programar() = println("programar")
}

class Jornalista : Pessoa() {
    fun escreverNoticia() = println("Escrever noticia")
}

class FuncionarioPublico : Pessoa(){

}

fun main() {

    val desenvolvedorAndroid = DesenvolvedorAndroid()
    desenvolvedorAndroid.comer()
    desenvolvedorAndroid.candidatarSe()

    println("---------------")

    val jornalista = Jornalista()
    jornalista.comer()

}