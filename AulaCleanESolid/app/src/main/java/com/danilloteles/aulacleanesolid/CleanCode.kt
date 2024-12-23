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

/*data class Funcionario(
    var salario: Double,
    var margemContribuição: Double
){
    fun isentoDescontoSalario() : Boolean {
        return this.salario <= 2000 && this.margemContribuição <= 12
    }
}*/

/*data class Produto(
    var nome: String,
    var quantidade: Int,
    var preco: Double
)*/

/*class Pedido {
    fun calcularPrecoTotalItens( itens: List<Produto> ) : Double {
        //for -> quantidade * preço -> calcular o total dos itens
        //Retorna o total
        return 3200.0
    }
    fun aplicarDesconto ( total: Double, desconto: Double ) : Double{
        //código de regras
        return total - desconto
    }
}*/

fun main() {

    /*val itens = listOf(
        Produto("notebook", 1, 1200.0),
        Produto("celular", 2, 1000.0),
    )*/
    //val pedido = Pedido()
    /*val totalPedido = pedido.calcularPrecoTotalItens( itens )
    val totalComDesconto = pedido.aplicarDesconto( totalPedido, 200.0 )*/

    /*val nomesUsuarios = listOf("jamilton", "marcos", "mariana")

    val nomesFormatados = nomesUsuarios.map { nome ->
        "+) $nome"
    }
    println(nomesFormatados)*/

    /*val nomesUsuariosFormatados = mutableListOf<String>()
    for (nome in nomesUsuarios){
        nomesUsuariosFormatados.add("+) $nome")
    }
    println(nomesUsuariosFormatados)*/

    /*val funcionario = Funcionario(1200.0, 10.0)

    if ( funcionario.isentoDescontoSalario() ) {
        //Quem é isento
    }else{

    }*/

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