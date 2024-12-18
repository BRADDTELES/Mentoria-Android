package com.application.aulaapicommvvm.data.repository

import androidx.lifecycle.MutableLiveData
import com.application.aulaapicommvvm.data.model.PostagemResposta
import com.application.aulaapicommvvm.domain.model.Postagem

interface IPostagemRepository {//Contrato, Conceito ou abstração
    suspend fun recuperarPostagens() : List<Postagem>
}