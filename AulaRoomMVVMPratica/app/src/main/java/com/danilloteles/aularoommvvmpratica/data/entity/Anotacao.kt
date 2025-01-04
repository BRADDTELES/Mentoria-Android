package com.danilloteles.aularoommvvmpratica.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "anotacoes",
    foreignKeys = [
        ForeignKey(
            entity = Categoria::class,
            parentColumns = ["id_categoria"],
            childColumns = ["id_categoria"]
        )
    ]
)
data class Anotacao(
    @PrimaryKey( autoGenerate = true )
    @ColumnInfo( name = "id_anotacao" )
    val idAnotacao: Long,
    @ColumnInfo( name = "id_categoria" )
    val idCategoria: Long,
    val titulo: String,
    val descricao: String
)
