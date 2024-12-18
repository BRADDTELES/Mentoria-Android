package com.application.aulacomponenteslistagemcolecoes.teste

data class Produto(
    var nome: String,
    var preco: Double
){
    fun desativar(){
        println("Produto $nome com preço: $preco foi desativado")
    }
}

fun salvarProduto( produto: Produto ){

}

class AlertaMensagem {
    fun configurarTitulo( titulo: String ) = println(titulo)
    fun configurarDescricao( descricao: String ) = println(descricao)

    fun configurarCancelar() = println("Ação de cancelar")
    fun configurarConfirmar() = println("Ação de confirmar")
}

fun main() {

    val alertaMensagem = AlertaMensagem()
    /*alertaMensagem.configurarTitulo("Confirmar salvar?")
    alertaMensagem.configurarDescricao("Você tem certeza...?")
    alertaMensagem.configurarCancelar()
    alertaMensagem.configurarConfirmar()*/

    /*alertaMensagem.apply {
        configurarTitulo("Confirmar salvar?")
        configurarDescricao("Você tem certeza...?")
        configurarCancelar()
        configurarConfirmar()
    }*/

    val lista = listOf("jamilton", "ana", "pedro")

    lista
        .map { it.uppercase() }
        .also {
            println(it)
        }


    //var produto: Produto? = null


    //Usuario é que pode escolher ou não o produto
    //produto = Produto("Notebook", 1200.00)

    /*produto?.let { produto ->
        salvarProduto(produto)
    }*/

    /*produto?.run {
        desativar()
        // posso realizar algumas operações
    }*/

    /*val novoObjeto = with( produto ){
        desativar()
        this //retornar o objeto
    }*/

    /*if ( produto != null ){
        produto.preco = 1100.00
        salvarProduto( produto )
    }*/

    /*var novoProduto = produto?.let { it ->
        it.preco = 1100.00
        //salvarProduto( produto )
        it
    }*/
    /*produto?.let {
        it.preco = 1100.00
        salvarProduto( produto )
    }

    println( produto?.nome )
    println( produto?.preco )*/

}