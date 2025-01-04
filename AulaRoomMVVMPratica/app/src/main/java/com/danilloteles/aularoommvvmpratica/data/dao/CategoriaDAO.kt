package com.danilloteles.aularoommvvmpratica.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria

@Dao
interface CategoriaDAO {

    @Insert
    fun salvar( categoria: Categoria ) : Long

    @Query("SELECT * FROM categorias")
    fun listar() : List<Categoria>

}