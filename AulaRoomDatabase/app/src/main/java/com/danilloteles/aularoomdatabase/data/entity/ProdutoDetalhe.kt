package com.danilloteles.aularoomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "produto_datalhes",
    indices = [
        Index(value = ["id_produto"], unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = Produto::class, //parent Entity
            parentColumns = ["id_produto"], //parent column
            childColumns = ["id_produto"], //child column
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
    //ignoredColumns = ["marca", "descricao"]
)
data class ProdutoDetalhe(
    @PrimaryKey( autoGenerate = true )
    @ColumnInfo( name = "id_produto_detalhe" )
    val idProdutoDetalhe: Long,
    @ColumnInfo( name = "id_produto" )
    val idProduto: Long,
    val marca: String,
    val descricao: String
)
