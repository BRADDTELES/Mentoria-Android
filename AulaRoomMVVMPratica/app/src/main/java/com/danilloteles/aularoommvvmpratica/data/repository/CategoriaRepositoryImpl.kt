package com.danilloteles.aularoommvvmpratica.data.repository

import com.danilloteles.aularoommvvmpratica.data.dao.CategoriaDAO
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria
import javax.inject.Inject

class CategoriaRepositoryImpl @Inject constructor(
    private val categoriaDao: CategoriaDAO
) : CategoriaRepository{
    override suspend fun salvar(categoria: Categoria) : ResultadoOperacao {

        val idCategoria = categoriaDao.salvar( categoria )
        if ( idCategoria > 0) {
            return ResultadoOperacao(
                true, "Categoria cadastrada com sucesso"
            )
        }
        return ResultadoOperacao(
            true, "Erro ao cadastrar categoria"
        )

    }
}