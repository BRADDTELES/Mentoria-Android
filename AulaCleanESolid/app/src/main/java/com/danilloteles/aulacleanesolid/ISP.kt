package com.danilloteles.aulacleanesolid

class CuidadorAves(
    private val ave: Ave
){
    fun treinar() {

    }
}

interface Ave {// Interface generica
    fun emitirSom()
}

interface AveQueVoa : Ave {// Interface especifica
    fun voar()
}

class Gaviao : AveQueVoa {
    override fun voar() {

    }

    override fun emitirSom() {

    }

}

class Tucano : AveQueVoa {
    override fun voar() {

    }

    override fun emitirSom() {

    }

}

class Pinguim : Ave {

    override fun emitirSom() {

    }

}

fun main() {

    val gaviao = Gaviao()//ave é generico -> Gavião é especifico
    val tucano = Tucano()
    val pinguim = Pinguim()

    val cuidadorGaviao = CuidadorAves( pinguim )

}