package com.danilloteles.aularoommvvmpratica.data.repository

import com.danilloteles.aularoommvvmpratica.data.entity.Anotacao
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria
import com.danilloteles.aularoommvvmpratica.data.entity.relacionamentos.AnotacaoECategoria

interface AnotacaoRepository {
    suspend fun salvar( anotacao: Anotacao ) : ResultadoOperacao
    suspend fun atualizar( anotacao: Anotacao ) : ResultadoOperacao
    suspend fun remover( anotacao: Anotacao ) : ResultadoOperacao
    suspend fun listarAnotacaoECategoria() : List<AnotacaoECategoria>
    suspend fun pesquisarAnotacaoECategoria(texto: String) : List<AnotacaoECategoria>
}