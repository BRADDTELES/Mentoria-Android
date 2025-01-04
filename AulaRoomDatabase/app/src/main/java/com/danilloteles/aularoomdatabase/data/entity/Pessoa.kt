package com.danilloteles.aularoomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "pessoas"
)
data class Pessoa(
    @PrimaryKey( autoGenerate = true )
    @ColumnInfo( name = "id_pessoa" )
    val idPessoa: Long,
    val nome: String,
    val sexo: String
)
