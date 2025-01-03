package com.danilloteles.aularoomdatabase.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    @ColumnInfo("nome_sobrenome")
    val nomeSobrenome: String,
    val senha: String,
    val idade: Int,
    val peso: Double,
    //@Ignore val imc: Double
    /*@Embedded
    val endereco: Endereco,*/
    val data: Date?/*,//Banco: long - App: Date
    @ColumnInfo(name = "sexo_usuario", defaultValue = "")
    var sexoUsuario: String = ""*/
)

@ProvidedTypeConverter
class Conversor {
    @TypeConverter
    fun converterParaLong( data: Date ) : Long? {
        return data?.time
    }

    @TypeConverter
    fun converterParaDate( data: Long ) : Date? {
        return data?.let { dtLong ->
            Date( data )
        }
    }
}

/*
data class Endereco (
    val rua: String,
    val numero: Int
)*/
