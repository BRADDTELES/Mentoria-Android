package com.application.aulacomponenteslistagemcolecoes.teste

class Matematica {
    fun somar( n1: Int, n2: Int ): Int{
        return n1 + n2
    }
}

//Função
/*fun somar( n1: Int, n2: Int ): Int{
    *//* mas poderia fazer várias outras coisas *//*
    return n1 + n2
}*/

fun calcular( funcao: (Int, Int) -> Int ){

    val retorno = funcao(10 ,10)
    println( retorno )

}

/*fun  executar(){
    println("Executar")
}

fun executar2() = println("Executar2")*/

/*class Botao {
    fun configurarCliqueBotao( nome: String, funcao: () -> Unit ){
        funcao()
        println("Nome: $nome")
    }
}*/

class Botao {
    fun configurarCliqueBotao( funcao: (String) -> Unit ){
        funcao("Danillo")
    }
}



fun main() {

    val botao = Botao()
    /*botao.configurarCliqueBotao("Danillo") {
        println("Executou função lambda")
    }*/
    botao.configurarCliqueBotao {
        println("Executou função lambda executou nome: $it")
    }

    //Função Lambda
    /*val funcaoLambda = { nome: String, idade: Int->
        println("Executar nome: $nome idade: $idade")
    }

    funcaoLambda("Danillo", 34)*/

    //calcular( ::somar )
    /*val matematica = Matematica()
    calcular( matematica::somar )*/

    /*val matematica = Matematica()
    val retorno = matematica.somar(10, 10)*/
    //val retorno = somar(10, 10)
    //println( retorno )

}