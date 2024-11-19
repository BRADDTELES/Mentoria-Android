package com.application.aulaapicommvvm.presentation.view

/*class Usuario(
    val nome: String
){
    fun logar(){
        println("Usuário logado: ${this.nome}")
    }

    *//*fun deslogar(){
        println("Usuário deslogado: ${this.nome}")
    }*//*
}

fun Usuario.deslogar(){
    println("Usuário deslogado: ${this.nome}")
}*/

/*fun String.adicionarSimboloMais() : String{
    //println("+ ${this}")
    return "+ $this"
}*/

fun List<String>.adicionarSimboloMais() : List<String>{

    val minhaLista = mutableListOf<String>()
    this.forEach { item ->
        //println("+ $item")
        minhaLista.add("+ $item")
    }
    return minhaLista
}
fun main() {

    val lista = listOf("Jamilton", "Ana", "Pedro")
    val novaLista = lista.adicionarSimboloMais()
    println(novaLista)

    /*val nome: String = "Jamilton"
    *//*println( nome.toString() )*//*
    val valor = nome.adicionarSimboloMais()
    println( valor )*/

    /*val usuario = Usuario("Jamilton")
    usuario.logar()
    usuario.deslogar()*/
}