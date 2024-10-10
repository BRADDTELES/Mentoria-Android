package com.application.aulacomponenteslistagemcolecoes.teste

/*class Motorista ( val nome: String ){
    fun exibirDadosMotorista() = println("Motorista: $nome")

    inner class Caminhao( val nomeCaminhao: String ){//Classe aninhada (Nested Class)
        fun exibirDadosCaminhao() = println("Caminhão: $nomeCaminhao motorista: $nome")
    }
}*/

data class Pergunta( val pergunta: String, val respostaCerta: Int )

fun main() {

    val pergunta1 = Pergunta( "Qual a pergunta?", 1 )
    val pergunta2 = Pergunta( "Qual a pergunta?", 1 )

    println( pergunta1 == pergunta2 )

    /*val motorista = Motorista("Danillo")
    val caminhao = motorista.Caminhao("FH 60")
    caminhao.exibirDadosCaminhao()*/

    /*val caminhao = Motorista.Caminhao("FH 500")
    caminhao.exibirDadosCaminhao()*/

    /*val motorista = Motorista("Danillo")
    motorista.exibirDadosMotorista()*/

}