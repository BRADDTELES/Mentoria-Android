package com.application.aulacomponenteslistagemcolecoes.teste

fun converterMaiuscula( texto: String ) : String {
    return texto.uppercase()
}

fun main() {

    val lista = listOf("jamilton", "pedro", "ana", "maria")

    /*val novaLista = mutableListOf<String>()
    lista.forEach { nome ->
        novaLista.add( nome.uppercase() )
        //println( nome.uppercase() )
    }

    println( novaLista )*/

    /*val novaLista = lista.map { nome ->
        //nome.uppercase()
        //"+ ${nome.uppercase()}"
        //converterMaiuscula(nome)
        //"+${converterMaiuscula(nome)}"
    }*/
    val novaLista = lista.map {
        //it.uppercase()
        "+ ${it.uppercase()}"
    }
    println( novaLista )

    /*for ( nome in lista ){
        println( nome )
    }*/
}