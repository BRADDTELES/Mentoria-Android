package com.application.aprendendokotlindozero

class Jogador{

    var kart = ""
    var pneu = ""
    var planador = ""

    fun acelerar( aceleracao: Int = 0 ): String = "acelerar na velocidade: $aceleracao "

    fun retornarPoder(): String {
        return "Casco vermelho"
    }
}

fun main() {

    val jogador = Jogador()
    jogador.kart = "Kart do Mario"
    println(jogador.kart)
    var acelerar = jogador.acelerar(aceleracao = 90)
    println(acelerar)
    val poder = jogador.retornarPoder()
    println( poder )

}