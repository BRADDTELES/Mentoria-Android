package com.danilloteles.aularoommvvmpratica.data.repository

import com.danilloteles.aularoommvvmpratica.data.entity.Anotacao

interface AnotacaoRepository {
    suspend fun salvar( anotacao: Anotacao ) : ResultadoOperacao
}