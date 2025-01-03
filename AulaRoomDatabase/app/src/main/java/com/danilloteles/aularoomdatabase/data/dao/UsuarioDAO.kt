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

    //@Query("SELECT * FROM usuarios ORDER BY nome_sobrenome ASC")
    @Query("SELECT * FROM usuarios ")
    fun listar() : List<Usuario>

    //@Query("SELECT * FROM usuarios WHERE id >= :textoPesquisa AND id <= 10")
    //@Query("SELECT * FROM usuarios WHERE id BETWEEN 1 AND 20 ")
    //@Query("SELECT * FROM usuarios WHERE id IN(1, 2 , 6) ")
    //@Query("SELECT * FROM usuarios WHERE id = :textoPesquisa ")
    @Query("SELECT * FROM usuarios WHERE nome_sobrenome LIKE '%' || :textoPesquisa || '%' ")
    fun filtrar( textoPesquisa: String ) : List<Usuario>

}