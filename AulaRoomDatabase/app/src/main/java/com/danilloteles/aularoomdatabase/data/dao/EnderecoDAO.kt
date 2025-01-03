package com.danilloteles.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.danilloteles.aularoomdatabase.data.model.Endereco
import com.danilloteles.aularoomdatabase.data.model.Usuario

@Dao
interface EnderecoDAO {

    @Insert
    fun salvar( endereco: Endereco )

    @Delete
    fun remover( endereco: Endereco )

    @Update
    fun atualizar( endereco: Endereco )

}