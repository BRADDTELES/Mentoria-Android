package com.application.aulacomponenteslistagemcolecoes.teste

import java.io.Serializable


//DRY -> Don't Repeat Yourself (não repita código)
// Divisão de responsabilidades (ok)
// Classes devem UTILIZAR outras classes (ok)
// Alto Acoplamento e Baixo Acoplamento (ok)
class Musico( val instrumento: Instrumento ) {
    fun tocar(){
        println("Musico tocando")
        instrumento.sendoTocado()
    }
}

//Herança e Interface (Contrato) -> Composição
interface Instrumento{
    fun sendoTocado()
}
// Int e String - tipos: Violao e Bateria
class Violao : Instrumento {//DOIS TIPOS: Violaao, Instrumento
    override fun sendoTocado(){
        println("Utilizando cordas (20 linhas de código)")
        println("para tocar o violão")
    }
    fun ajustarCordas(){
        println("Ajustar cordas")
    }
}

class Bateria : Instrumento {
    override fun sendoTocado(){
        println("Utilizando baquetas (20 linhas de código)")
        println("para tocar a bateria")
    }
    fun ajustarBaqueta(){
        println("Ajustar baqueta")
    }
}

class Guitarra : Instrumento {
    override fun sendoTocado() {
        println("Utilizando cordas (20 linhas de código)")
        println("Utilizando também ajuste de som")
        println("para tocar a guitarra")
    }
}

class Fornecedor : Serializable {

}

class Intent {
    fun putExtra( chave: String, serializable: Serializable ){

    }
}

fun main() {

    val fornecedor = Fornecedor()
    val intent = Intent()
    intent.putExtra("fornecedor", fornecedor )

    //val violao: Instrumento = Violao()
    //val bateria: Instrumento = Bateria()

    // Tela 1 (violão)
    /*val violao1 = Violao()
    val musico1 = Musico( violao1 )
    musico1.tocar()

    println("+++++++++++++++")

    // Tela 2 (violão)
    val violao2 = Violao()
    val musico2 = Musico( violao2 )
    musico2.tocar()

    println("+++++++++++++++")

    // Tela 3 (bateria)
    val violaoJamilton: Violao = Violao()
    val bateria1 = Bateria()
    val musico3 = Musico( bateria1 )//Bateria como Instrumento (método de instrumento)
    musico3.tocar()

    println("+++++++++++++++")

    // Tela 4 (guitarra)
    val guitarra = Guitarra()
    val musico4 = Musico( guitarra )
    musico4.tocar()*/
}