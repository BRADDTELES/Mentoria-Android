package com.danilloteles.aulacleanesolid

class BancoDados {//Baixo nivel
    fun conectar() {
        println("conectado banco de dados")
    }
}

class Usuario(//Alto nivel
    private val bancoDados: BancoDados
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



}