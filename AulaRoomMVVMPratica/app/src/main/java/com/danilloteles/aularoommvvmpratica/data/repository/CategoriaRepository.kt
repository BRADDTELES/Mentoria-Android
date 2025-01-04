package com.danilloteles.aularoommvvmpratica.data.repository

import com.danilloteles.aularoommvvmpratica.data.entity.Categoria

interface CategoriaRepository {
    suspend fun salvar( categoria: Categoria ) : ResultadoOperacao
}