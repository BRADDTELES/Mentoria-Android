package com.danilloteles.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.danilloteles.aularoomdatabase.data.model.Usuario

@Dao
interface UsuarioDAO {
    @Insert
    fun salvar( usuario : Usuario )
}