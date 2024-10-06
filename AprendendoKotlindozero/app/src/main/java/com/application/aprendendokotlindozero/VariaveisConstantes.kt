package com.application.aprendendokotlindozero

fun  retornaNomeUsuario(){
    //Executa e retorna algo
}

/*class Usuario{

}*/
    const val  nomeUsuario = "danillo"//tempo de compilação

fun main() {
    /* var (mutável) -> posso mudar o valor
    * val (imutável) -> não posso mudar o valor */
    val nome = retornaNomeUsuario()//tempo de execução

    val numero1 = 20
    val numero2 = 30
    val resultado = numero1 + numero2

    val numero: Int = 20
    //val usuario = Usuario()

    println(nome)
}