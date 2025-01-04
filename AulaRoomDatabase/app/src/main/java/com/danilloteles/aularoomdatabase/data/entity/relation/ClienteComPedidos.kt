package com.danilloteles.aularoomdatabase.data.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.danilloteles.aularoomdatabase.data.entity.Cliente
import com.danilloteles.aularoomdatabase.data.entity.Pedido

data class ClienteComPedidos(
    @Embedded
    val cliente: Cliente,
    @Relation(
        parentColumn = "id_cliente",
        entityColumn = "id_cliente",
        entity = Pedido::class
    )
    val pedidos: List<Pedido>
)
