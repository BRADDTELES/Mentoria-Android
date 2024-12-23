package com.danilloteles.aulacleanesolid

/*
produto: nome, descrição, fotos, preco
item: nome, preço, quantidade
* */

/*class CarrinhoCompra {

    private val itens: MutableList<Item> = mutableListOf()
    private var statusPedido = StatusPedido.ANDAMENTO

    fun adicionarItem( item: Item ) {
        itens.add( item )
    }

    fun recuperarItens() : MutableList<Item> {
        return this.itens
    }

    fun confirmarPedidos() : Boolean {
        //Atualização confirmando o pediddo
        if (this.itens.size > 0) {
            this.statusPedido = StatusPedido.CONFIRMADO
            this.enviarEmailConfirmacao()
            return true
        }
        return false
    }

    fun exibirStatusPedido() : StatusPedido {
        return this.statusPedido
    }

    fun enviarEmailConfirmacao() : Boolean {
        //Código do envio do e-mail
        return true
    }

}*/

data class Item(
    val nome: String,
    val preco: Double,
    val quantidade: Int
)

enum class StatusPedido {
    ANDAMENTO, CONFIRMADO
}

class CarrinhoCompras {

    private val itens: MutableList<Item> = mutableListOf()

    fun adicionarItem( item: Item ) {
        itens.add( item )
    }

    fun recuperarItens() : MutableList<Item> {
        return this.itens
    }

    fun recuperarQuantidadeItens() : Int {
        return this.itens.size
    }
}

class Pedido(
    private val carrinhoCompra: CarrinhoCompras,//Injeção de dependencia
    private val emailServico: EmailServico,//Injeção de dependencia
) {
    private var statusPedido = StatusPedido.ANDAMENTO

    fun confirmarPedidos() : Boolean {
        //Atualização confirmando o pediddo
        if ( this.carrinhoCompra.recuperarQuantidadeItens() > 0) {
            this.statusPedido = StatusPedido.CONFIRMADO
            this.emailServico.enviarEmailConfirmacao()
            return true
        }
        return false
    }
}

class EmailServico {
    fun enviarEmailConfirmacao() : Boolean {
        //Código do envio do e-mail
        return true
    }
}

fun main() {

    val carrinho = CarrinhoCompras()
    val emailServico = EmailServico()
    val pedido = Pedido( carrinho, emailServico )

    val notebook = Item("Notebook", 1200.0, 1)
    val caneca = Item("Caneca", 40.0, 2)

    carrinho.adicionarItem( notebook )
    carrinho.adicionarItem( caneca )

    /*carinho.confirmarPedidos()
    carinho.exibirStatusPedido()*/
    pedido.confirmarPedidos()
    carrinho.recuperarItens()

}