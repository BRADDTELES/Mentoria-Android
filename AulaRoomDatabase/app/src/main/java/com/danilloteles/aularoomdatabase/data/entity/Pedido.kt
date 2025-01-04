package com.danilloteles.aularoomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pedidos",
    foreignKeys = [
        ForeignKey(
            entity = Cliente::class, //parent Entity
            parentColumns = ["id_cliente"], //parent column
            childColumns = ["id_cliente"], //child column
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Pedido(
    @PrimaryKey( autoGenerate = true )
    @ColumnInfo( name = "id_pedido" )
    val idPedido: Long,
    @ColumnInfo( name = "id_cliente" )
    val idCliente: Long,
    val produto: String,
    val preco: Double
)
