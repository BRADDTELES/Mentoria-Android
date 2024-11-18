package com.application.aulaapicommvvm.data.repository

import androidx.lifecycle.MutableLiveData
import com.application.aulaapicommvvm.data.model.Postagem

interface IPostagemRepository {//Contrato, Conceito ou abstração
    val listaPostagensRepository: MutableLiveData<List<Postagem>>
    suspend fun recuperarPostagens()
}