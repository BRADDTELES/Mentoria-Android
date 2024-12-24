package com.danilloteles.aulacleanesolid

interface Instrumento {
    fun sendoTocado()
}

class Bateria : Instrumento {
    fun sendoTocadoBateria(){
        println("bateria sendo tocado")
    }

    override fun sendoTocado() {
        sendoTocadoBateria()
    }
}

class Violao : Instrumento {
    fun sendoTocadoViolao(){
        println("violao sendo tocado")
    }

    override fun sendoTocado() {
        sendoTocadoViolao()
    }
}

class Musico (
    private val instrumento: Instrumento
){
    fun tocar() {
        println("Musico tocando instrumento: ")
        instrumento.sendoTocado()
    }
}

fun main() {

    val bateria = Bateria()
    val violao = Violao()

    val musico = Musico( bateria )

    musico.tocar()

}