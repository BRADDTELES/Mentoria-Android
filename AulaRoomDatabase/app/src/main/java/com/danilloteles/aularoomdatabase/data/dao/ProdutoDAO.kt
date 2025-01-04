package com.danilloteles.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.danilloteles.aularoomdatabase.data.entity.Produto
import com.danilloteles.aularoomdatabase.data.entity.ProdutoDetalhe
import com.danilloteles.aularoomdatabase.data.entity.relation.ProdutoEProdutoDetalhe

@Dao
interface ProdutoDAO {

    @Insert
    fun salvarProduto( produto: Produto ) : Long

    @Insert
    fun salvarProdutoDetalhe( produtoDetalhe: ProdutoDetalhe ) : Long

    @Query("SELECT * FROM produtos")
    fun listarProdutos() : List<Produto>

    @Query("SELECT * FROM produto_datalhes")
    fun listarProdutoDetalhes() : List<ProdutoDetalhe>

    @Transaction
    @Query("SELECT * FROM produtos")
    fun listarProdutosEProdutoDetalhes() : List<ProdutoEProdutoDetalhe>

}