package com.danilloteles.aularoommvvmpratica.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.danilloteles.aularoommvvmpratica.data.entity.Anotacao
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria

@Dao
interface AnotacaoDAO {

    @Insert
    fun salvar( anotacao: Anotacao ) : Long

    @Query("SELECT * FROM anotacoes")
    fun listar() : List<Anotacao>

}