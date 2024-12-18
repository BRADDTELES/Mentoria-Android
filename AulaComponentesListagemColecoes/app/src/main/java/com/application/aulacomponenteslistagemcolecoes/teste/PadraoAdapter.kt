package com.application.aulacomponenteslistagemcolecoes.teste

class TomadaAntiga( val conector: Conector ) {
    fun passarEnergia() {
        val qtdPinos = conector.quantidadePinos()
        if ( qtdPinos == 2 ){
            conector.ligarAparelho()
            println("Quantidade de pinos: $qtdPinos")
            println("passando energia")
        }else{
            println("Essa tomada só funciona com 2 pinos, você passou: $qtdPinos")
        }
    }
}


interface Conector{
    fun quantidadePinos() : Int
    fun ligarAparelho()
}

class ConectorAdaptador( val conectorNovoPadrao: ConectorNovoPadrao ) : Conector {
    override fun quantidadePinos(): Int {
        return 2
    }

    override fun ligarAparelho() {
        conectorNovoPadrao.ligarAparelho()
    }

}

class ConectorNovoPadrao : Conector{
    override fun quantidadePinos(): Int {
        return 3
    }
    override fun ligarAparelho(){
        println("aparelho está ligado")
        println("mais de 20 linhas de código")
    }
}

fun main() {

    val conectorNovoPadrao = ConectorNovoPadrao()

    val conectorAdaptador = ConectorAdaptador( conectorNovoPadrao )

    val tomadaAntiga = TomadaAntiga( conectorAdaptador )
    tomadaAntiga.passarEnergia()

}