package com.danilloteles.aularoommvvmpratica.data.entity.relacionamentos

import androidx.room.Embedded
import androidx.room.Relation
import com.danilloteles.aularoommvvmpratica.data.entity.Anotacao
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria

data class AnotacaoECategoria(
    @Embedded
    val anotacao: Anotacao,
    @Relation(
        entityColumn = "id_categoria",
        parentColumn = "id_categoria"
    )
    val categoria: Categoria
)