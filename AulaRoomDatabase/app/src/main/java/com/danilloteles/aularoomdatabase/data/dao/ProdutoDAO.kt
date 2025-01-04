package com.danilloteles.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.danilloteles.aularoomdatabase.data.model.Produto
import com.danilloteles.aularoomdatabase.data.model.ProdutoDetalhe
import com.danilloteles.aularoomdatabase.data.model.Usuario

@Dao
interface ProdutoDAO {

    @Insert
    fun salvarProduto( produto: Produto ) : Long

    @Insert
    fun salvarProdutoDetalhe( produtoDetalhe: ProdutoDetalhe ) : Long

}