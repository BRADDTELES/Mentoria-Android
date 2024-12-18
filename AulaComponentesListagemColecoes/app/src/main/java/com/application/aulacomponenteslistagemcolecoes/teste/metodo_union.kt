package com.application.aulacomponenteslistagemcolecoes.teste

fun main() {

    val listaLanches = listOf("Hamburguer", "Batata-frita")
    val listaEntradas = listOf("Coxa de frango", "Pipoca")

    //val novaLista = listaLanches.union( listaEntradas )
    val listaExclusao = listOf("Batata-frita", "Pipoca")
    val novaLista = listaEntradas + listaLanches
    val listaCompleta = novaLista - listaExclusao
    println( listaCompleta )
}