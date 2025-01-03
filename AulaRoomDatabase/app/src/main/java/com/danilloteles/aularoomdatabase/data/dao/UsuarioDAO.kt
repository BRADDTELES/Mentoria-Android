package com.danilloteles.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.danilloteles.aularoomdatabase.data.model.Usuario

@Dao
interface UsuarioDAO {
    @Insert
    fun salvar( usuario : Usuario )

    @Delete
    fun remover( usuario : Usuario )

    @Update
    fun atualizar( usuario : Usuario )

    @Query("SELECT * FROM usuarios ORDER BY nome_sobrenome ASC")
    fun listar() : List<Usuario>

}