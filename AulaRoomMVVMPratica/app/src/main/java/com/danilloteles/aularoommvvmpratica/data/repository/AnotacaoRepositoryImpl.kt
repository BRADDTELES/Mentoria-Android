package com.danilloteles.aularoommvvmpratica.data.repository

import com.danilloteles.aularoommvvmpratica.data.dao.AnotacaoDAO
import com.danilloteles.aularoommvvmpratica.data.entity.Anotacao
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
}