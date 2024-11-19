package com.application.aulainjecaodependenciashilt

class Carro( private val motor: Motor ) { // Injeção de dependencia "Baixo acoplamento"
//class Carro {

    //lateinit var motor: Motor

    fun ligar(){
        //val motor = Motor()//Alto acomplamento "não recomendado"
        motor.acionar()
        println("Carro ligado")
    }
}

interface Motor {
    fun acionar()
}

class MotorGasolina( val nome: String) : Motor{
    override fun acionar(){
        println("Motor ligado a gasolina")
    }
}

class MotorEletrico() : Motor{
    override fun acionar(){
        println("Motor ligado eletrico")
    }
}

fun main() {

    //Tela 01
    val motorGasolina = MotorGasolina("GHT2.0")
    val carro = Carro( motorGasolina )
    //val carro = Carro()
    //carro.motor = motorGasolina // Injeção de campo
    carro.ligar()

    //Tela 02
    val motorGasolina2 = MotorGasolina("GHT2.0")
    val carro2 = Carro( motorGasolina2 )
    carro2.ligar()

}