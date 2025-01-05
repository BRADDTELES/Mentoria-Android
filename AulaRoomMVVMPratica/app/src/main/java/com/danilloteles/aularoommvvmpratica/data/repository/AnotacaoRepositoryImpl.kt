package com.danilloteles.aularoommvvmpratica.data.repository

import com.danilloteles.aularoommvvmpratica.data.dao.AnotacaoDAO
import com.danilloteles.aularoommvvmpratica.data.entity.Anotacao
import com.danilloteles.aularoommvvmpratica.data.entity.relacionamentos.AnotacaoECategoria
import javax.inject.Inject

class AnotacaoRepositoryImpl @Inject constructor (
    private val anotacaoDAO: AnotacaoDAO
) : AnotacaoRepository {
    override suspend fun salvar(anotacao: Anotacao): ResultadoOperacao {
        val idAnotacao = anotacaoDAO.salvar( anotacao )
        if ( idAnotacao > 0) {
            return ResultadoOperacao(
                true, "Anotação cadastrada com sucesso"
            )
        }
        return ResultadoOperacao(
            true, "Erro ao cadastrar Anotação"
        )
    }

    override suspend fun remover(anotacao: Anotacao): ResultadoOperacao {
        val qtdRegistros = anotacaoDAO.remover( anotacao )
        if ( qtdRegistros > 0) {
            return ResultadoOperacao(
                true, "Anotação removida com sucesso"
            )
        }
        return ResultadoOperacao(
            true, "Erro ao remover Anotação"
        )
    }

    override suspend fun listarAnotacaoECategoria(): List<AnotacaoECategoria> {
        return anotacaoDAO.listarAnotacaoECategoria()
    }

    override suspend fun pesquisarAnotacaoECategoria(texto: String): List<AnotacaoECategoria> {
        return anotacaoDAO.pesquisarAnotacaoECategoria( texto )
    }
}