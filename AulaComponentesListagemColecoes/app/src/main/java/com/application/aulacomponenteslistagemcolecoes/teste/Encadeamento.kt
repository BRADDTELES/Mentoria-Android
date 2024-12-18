package com.application.aulacomponenteslistagemcolecoes.teste

class Menssagem( val menssagem: String, val duracao: Int ){
    companion object{
        const val DURACAO_CURTA = 0
        const val DURACAO_LONGA = 1

        fun construirTexto( menssagem: String, duracao: Int ) : Menssagem{
            return Menssagem( menssagem, duracao )
        }
    }

    fun exibir(){
        println("M: ${this.menssagem} - ${this.duracao}")
    }
}

fun main() {

    Menssagem.construirTexto(
        "Olá",
        Menssagem.DURACAO_LONGA
    ).exibir()

}