package com.danilloteles.aulamodulos.di

import com.danilloteles.domain.GetPedidoUseCase

object DomainModule {

    fun providePedidoUseCase() : GetPedidoUseCase {
        return GetPedidoUseCase()
    }
}