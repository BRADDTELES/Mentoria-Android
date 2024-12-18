package com.application.aulaapicommvvm.presentation.view

class MinhaPostagem(){
    operator fun invoke(novoNome: String): List<String>{
        return listOf("Jamilton", "Ana", "Pedro", novoNome)
    }
}

fun main() {

    val postagem = MinhaPostagem()
    val lista = postagem("Maria")
    println( lista )

}