package com.application.aprendendokotlindozero

class ContaBancaria constructor(agencia: String = "", conta: String = "", senha: String = "") {

    var usuarioAutenticado: Boolean = false
    var saldo: Double = 0.0

    init {
        this.usuarioAutenticado = true
    }

    fun recuperarSaldo(): Double? {
        if (usuarioAutenticado) {
            return saldo
        } else {
            return null
        }
    }

    fun depositar(saldo: Double) {
        this.saldo += saldo
    }

    fun sacar(saldo: Double) {
        this.saldo -= saldo
    }

    fun extrato(dias: Int) {
        println("Extrato dos últimos $dias dias")
    }

    fun extrato(dataInicial: String, dataFinal: String) {
        println("Extrato intervalo $dataInicial e $dataFinal")
    }
}

fun main() {

    var conta = ContaBancaria()

    var saldo = conta.recuperarSaldo()
    if (saldo != null) {
        println("Saldo: ${conta.recuperarSaldo()}")
        conta.depositar(200.0)
        conta.sacar(500.0)
        println("Saldo: ${conta.recuperarSaldo()}")
        conta.extrato(5)
        conta.extrato("10/05/2022", "10/06/2022")
    } else {
        println("Acesso negado!!! Usuário não autenticado.")
    }
}