package com.danilloteles.aularoomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "enderecos")
data class Endereco (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_endereco")
    val idEndereco: Int,
    val rua: String
)
