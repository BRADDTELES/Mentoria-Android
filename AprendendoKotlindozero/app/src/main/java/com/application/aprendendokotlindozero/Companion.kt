package com.application.aprendendokotlindozero

/*class Carro(
    var modelo: String = "",
    var velocidade: Int = 0
){

    companion object {
        const val VELOCIDADE_MAX_PERMITIDA = 120
        fun exibirMensagemVelocidadeMaxima(){
            println("A velocidade máxima é $VELOCIDADE_MAX_PERMITIDA")
        }
    }

    fun exibirInformacoes(){
        println("Informações: $modelo e $velocidade")
    }
}*/

class Usuario{

    companion object {
        fun verificarUsuarioLogado(): Boolean{
            return true
        }
    }
}

fun main() {

    val retorno = Usuario.verificarUsuarioLogado()
    println("usuario logado: $retorno")

    //Criando uma instancia ou objeto
    /*println("Velocidade máxima: ${Carro.VELOCIDADE_MAX_PERMITIDA}")
    Carro.exibirMensagemVelocidadeMaxima()

    val fusca = Carro("Fusca", 100)
    fusca.exibirInformacoes()

    val ferrari = Carro("Ferrari", 200)
    ferrari.exibirInformacoes()

    println("PI: ${Math.PI}")*/
}