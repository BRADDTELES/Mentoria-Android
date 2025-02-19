package com.danilloteles.aularoommvvmpratica.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.danilloteles.aularoommvvmpratica.data.entity.Anotacao
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria
import com.danilloteles.aularoommvvmpratica.data.entity.relacionamentos.AnotacaoECategoria

@Dao
interface AnotacaoDAO {

    @Insert
    fun salvar( anotacao: Anotacao ) : Long

    @Delete
    fun remover( anotacao: Anotacao ) : Int//quantidade de registros atualizados

    @Update
    fun atualizar( anotacao: Anotacao ) : Int

    @Query("SELECT * FROM anotacoes")
    fun listar() : List<Anotacao>

    @Query("SELECT * FROM anotacoes")
    fun listarAnotacaoECategoria() : List<AnotacaoECategoria>

    @Query("SELECT * FROM anotacoes a " +
            "WHERE a.titulo LIKE '%' || :texto || '%' " +
            "OR a.descricao LIKE '%' || :texto || '%' ")
    fun pesquisarAnotacaoECategoria( texto: String ) : List<AnotacaoECategoria>
}