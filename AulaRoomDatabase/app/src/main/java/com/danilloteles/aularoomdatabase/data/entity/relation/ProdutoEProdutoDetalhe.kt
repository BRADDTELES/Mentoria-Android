package com.danilloteles.aularoomdatabase.data.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.danilloteles.aularoomdatabase.data.entity.Produto
import com.danilloteles.aularoomdatabase.data.entity.ProdutoDetalhe

data class ProdutoEProdutoDetalhe(
    @Embedded
    val produto: Produto,
    @Relation(
        parentColumn = "id_produto",
        entityColumn = "id_produto",
        entity = ProdutoDetalhe::class
    )
    val produtoDetalhe: ProdutoDetalhe
)
