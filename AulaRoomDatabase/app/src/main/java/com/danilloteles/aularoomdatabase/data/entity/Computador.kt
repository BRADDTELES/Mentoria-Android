package com.danilloteles.aularoomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "computadores"
)
data class Computador(
    @PrimaryKey( autoGenerate = true )
    @ColumnInfo( name = "id_computador" )
    val idComputador: Long,
    val modelo: String,
    val marca: String
)
