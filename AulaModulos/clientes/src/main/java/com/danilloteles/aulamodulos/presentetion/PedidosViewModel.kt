package com.danilloteles.aulamodulos.presentetion

import com.danilloteles.aulamodulos.di.DomainModule

class PedidosViewModel {

    fun recuperarDadosPedido(){
        val useCase = DomainModule.providePedidoUseCase()
        useCase.getPedido()
    }
}