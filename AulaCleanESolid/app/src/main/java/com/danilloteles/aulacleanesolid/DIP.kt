package com.danilloteles.aulacleanesolid

/*
classes não devem depender de outras classes concretas
* */


//Concreta
class BancoDados : ConectorBancoDados {//Baixo nivel
    override fun conectar() {
        println("conectado banco de dados")
    }
}

class BancoDadosOracle : ConectorBancoDados {//Baixo nivel
    override fun conectar() {
        println("conectado banco de dados")
    }
}

//Abstração
interface ConectorBancoDados {
    fun conectar()
}

//Concreta
class Usuario(//Alto nivel
    private val conectorBancoDados: ConectorBancoDados //Injeção de dependencia / Inversao de dependencia
) {

    fun enviarEmailRecuperacaoSenha() {
        if ( verificarSeExisteUsuario() ) {
            println("Existe usuário")
        }else{
            println("Usuário inexistente")
        }
    }

    private fun verificarSeExisteUsuario() : Boolean {
        //Conectar banco dados
        return true
    }

}

fun main() {

    val bancoDados = BancoDados()
    val bancoDadosOracle = BancoDadosOracle()

    val usuario = Usuario( bancoDadosOracle )

}