package com.danilloteles.aularoomdatabase.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    @ColumnInfo("nome_sobrenome")
    val nomeSobrenome: String,
    val senha: String,
    val idade: Int,
    val peso: Double,
    //@Ignore val imc: Double
) {
}