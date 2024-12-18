package com.application.aprendendokotlindozero

enum class Status {
    FUNCIONAMENTO,
    MANUTENCAO,
    QUEBRADO
}

interface Eletrificado {
    fun motorEletrico()
}

abstract class Veiculo constructor(
    var nome: String = "",
    var qtdRodas: Int = 0,
    var status: Status = Status.FUNCIONAMENTO
) {

    abstract fun acelerar()
    abstract fun acelerar(intensidade: Int)
    abstract fun recuperarStatus()
}

class Carro(nome: String, qtdRodas: Int) : Veiculo(nome, qtdRodas) {

    override fun acelerar() {
        println("Acelerar ${nome} com ${qtdRodas} rodas")
    }

    override fun acelerar(intensidade: Int) {
        println("Intensidade: ${intensidade}")
    }

    override fun recuperarStatus() {
        println("O status do veículo é: ${status}")
    }

    companion object {
        fun exibeMensagemVelocidadeMaximaLei() {
            println("Velocidade maxima permitida em lei")
        }
    }
}

class Moto(nome: String, qtdRodas: Int) : Veiculo(nome, qtdRodas), Eletrificado {

    override fun acelerar() {
        println("Acelerar ${nome} com ${qtdRodas} rodas")
        motorEletrico()
    }

    override fun acelerar(intensidade: Int) {
        println("Intensidade: ${intensidade}")
    }

    override fun recuperarStatus() {
        println("O status do veículo é: ${status}")
    }

    override fun motorEletrico() {
        println("Rodando com motor elétrico")
    }

    companion object {
        fun exibeMensagemVelocidadeMaximaLei() {
            println("Velocidade maxima permitida em lei")
        }
    }
}

fun main() {

    val carro = Carro("Gol", 4)
    carro.acelerar()
    carro.status = Status.MANUTENCAO
    if (carro.status == Status.MANUTENCAO) {
        carro.recuperarStatus()
    } else if (carro.status == Status.QUEBRADO) {
        carro.recuperarStatus()
    } else {
        carro.recuperarStatus()
    }
    Carro.exibeMensagemVelocidadeMaximaLei()
    println("------------------------------")
    val moto = Moto("Yamaha", 2)
    moto.acelerar()
    Moto.exibeMensagemVelocidadeMaximaLei()

}