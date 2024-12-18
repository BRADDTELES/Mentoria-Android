package com.app.aulakotlinago24

fun main() {

     /*// Null Safety (segurança de nulos)
    var nome: String? = null

    nome = "jamilton"

    nome?.length

    if ( nome != null ) {
        nome.length
    }*/

    /* // Operadores relacionais e lógicos */
    val idade = 18
    val compras = 100
    val frete = 20
    val total = 120
    /*// == -> Igual  != -> Diferente  > -> Maior  < -> Menor  >= -> Maior e Igual  <= -> Menor e Igual
    if (  compras >= 200  ) {
        println( "Voçê tem frete grátis" )
    }else {
        val precisaComprar =  200 - compras
        println("NÃO tem frete GRÁTIS, compre: $precisaComprar")
    }*/
    // AND/&& (E) - OR/|| (OU)
    //val resultado = compras >= 200 && idade >= 50
    val resultado = compras >= 200 || idade >= 50
    if (  resultado  ) {
        println( "Voçê tem frete grátis (>= 200 E >=50 idade)" )
    }else{
        val precisaComprar =  200 - compras
        println( "NÃO tem frete GRÁTIS, compre: $precisaComprar" )
    }

}