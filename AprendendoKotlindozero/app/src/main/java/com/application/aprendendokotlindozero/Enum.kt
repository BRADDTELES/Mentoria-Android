package com.application.aprendendokotlindozero

import kotlinx.coroutines.processNextEventInCurrentThread

// aguardando_aprovacao, pedido_realizado, pagamento_confirmado, pedido_enviado, pedido_entregue
enum class StatusPedido{
    AGUARDANDO_APROVACAO, // 0
    PEDIDO_REALIZADO, // 1
    PAGAMENTO_CONFIRMADO, // 2
    PEDIDO_ENVIADO, // 3
    PEDIDO_ENTREGUE // 4
}

class Pedido (
    var total: Double = 0.0,
    var itens: String = "",
    var statusPedido: StatusPedido = StatusPedido.AGUARDANDO_APROVACAO
){

}

fun main() {

    // Tela de compras
    val pedido = Pedido(125.90, "camiseta, livro")

    //Pagemento com cartão
    pedido.statusPedido = StatusPedido.PEDIDO_REALIZADO

    // Transportadora
    pedido.statusPedido = StatusPedido.PEDIDO_ENVIADO
    println( "StatusPedido: ${StatusPedido.PEDIDO_ENVIADO.ordinal}" )

    //Histórico de compras
    if ( pedido.statusPedido == StatusPedido.PEDIDO_REALIZADO ){
        println("O seu pedido foi REALIZADO")
    }else if ( pedido.statusPedido == StatusPedido.PEDIDO_ENVIADO ){
        println("O seu pedido foi ENVIADO")
    }else if( pedido.statusPedido == StatusPedido.PAGAMENTO_CONFIRMADO ){
        println("O seu pagamento foi CONFIRMADO")
    }else if( pedido.statusPedido == StatusPedido.PEDIDO_ENTREGUE ){
        println("O seu pedido foi ENTREGUE")
    }else if( pedido.statusPedido == StatusPedido.AGUARDANDO_APROVACAO ){
        println("O seu pedido está AGUARDANDO APROVAÇÃO")
    }else{
        println("O seu pedido está em ANDAMENTO")
    }
}