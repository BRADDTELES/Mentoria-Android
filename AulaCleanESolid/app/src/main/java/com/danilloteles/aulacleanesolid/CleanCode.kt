package com.danilloteles.aulacleanesolid

/*enum class Status {
    INICIO, ANDAMENTO, PROCESSANDO
}

fun contemNomePesquisa( nome: String, listaNomes: List<String> ) : Boolean {
    *//*if ( listaNomes.contains(nome) ) {
        return true
    }
    return false*//*
    return listaNomes.contains(nome)
}*/

/*enum class StatusArtigo {
    RASCUNHO, PUBLICADO, DESPUBLICADO
}

data class Artigo(
    var titulo: String,
    var status: StatusArtigo = StatusArtigo.RASCUNHO
){
    fun estaPublicado() : Boolean{
        return this.status == StatusArtigo.PUBLICADO
    }
}*/

data class Funcionario(
    var salario: Double,
    var margemContribuição: Double
){
    fun isentoDescontoSalario() : Boolean {
        return this.salario <= 2000 && this.margemContribuição <= 12
    }
}

fun main() {

    val funcionario = Funcionario(1200.0, 10.0)

    if ( funcionario.isentoDescontoSalario() ) {
        //Quem é isento
    }else{

    }

    //Nomes significativos

    /*
    val artigo = Artigo("Teste")
    artigo.status = StatusArtigo.PUBLICADO

    if ( artigo.estaPublicado() ) {

    }

    val usuarioEstaLogado = true
    if ( !usuarioEstaLogado ) {//usuário NÃO está logado

    }*/

    /*val listaNomes = listOf("jamilton", "marcos", "mariana")
    val contemNome = contemNomePesquisa( "jamilton", listaNomes )*/

    /*
    val dataAtual = "10/11/2025"
    val diasTrabalhados = 2

    val listaNomes = listOf("jamilton", "marcos", "mariana")
    for (nome in listaNomes){
        println(nome)
    }
    val contador = 10
    while ( contador <= 20 ){

    }
    val hipotenusa = 10

    val status = Status.INICIO
    if ( status == Status.ANDAMENTO ) {

    }

    try {

    }catch ( erroConexao: Exception ){

    }
     */
}