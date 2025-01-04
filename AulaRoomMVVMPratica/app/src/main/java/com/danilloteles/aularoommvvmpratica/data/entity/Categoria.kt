package com.danilloteles.aularoommvvmpratica.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categorias")
data class Categoria(
    @PrimaryKey( autoGenerate = true )
    @ColumnInfo( name = "id_categoria" )
    val idCategoria: Long,
    val nome: String
)
