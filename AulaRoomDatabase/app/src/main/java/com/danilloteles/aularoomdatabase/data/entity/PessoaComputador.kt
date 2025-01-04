package com.danilloteles.aularoomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "pessoas_computadores",
    primaryKeys = ["id_pessoa", "id_computador"]//Chave composta
)
data class PessoaComputador(
    @ColumnInfo( name = "id_pessoa" )
    val idPessoa: Long,
    @ColumnInfo( name = "id_computador" )
    val idComputador: Long
)
