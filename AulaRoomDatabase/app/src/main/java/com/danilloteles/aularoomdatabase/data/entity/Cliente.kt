package com.danilloteles.aularoomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "clientes"
)
data class Cliente(
    @PrimaryKey( autoGenerate = true )
    @ColumnInfo( name = "id_cliente" )
    val idCliente: Long,
    val nome: String,
    val sobrenome: String
)
