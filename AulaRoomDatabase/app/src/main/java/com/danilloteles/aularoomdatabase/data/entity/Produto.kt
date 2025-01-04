package com.danilloteles.aularoomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "produtos"
)
data class Produto(
    @PrimaryKey( autoGenerate = true )
    @ColumnInfo( name = "id_produto" )
    val idProduto: Long,
    val nome: String,
    val preco: Double
)
