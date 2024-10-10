package com.application.aulacomponenteslistagemcolecoes.teste

class Motorista ( val nome: String ){
    fun exibirDadosMotorista() = println("Motorista: $nome")

    inner class Caminhao( val nomeCaminhao: String ){//Classe aninhada (Nested Class)
        fun exibirDadosCaminhao() = println("Caminhão: $nomeCaminhao motorista: $nome")
    }
}

fun main() {

    val motorista = Motorista("Danillo")
    val caminhao = motorista.Caminhao("FH 60")
    caminhao.exibirDadosCaminhao()

    /*val caminhao = Motorista.Caminhao("FH 500")
    caminhao.exibirDadosCaminhao()*/

    /*val motorista = Motorista("Danillo")
    motorista.exibirDadosMotorista()*/

}