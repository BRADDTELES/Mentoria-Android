package com.danilloteles.aularoomdatabase.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "produto_datalhes",
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
    val idProduto: Long,
    val marca: String,
    val descricao: String
)
