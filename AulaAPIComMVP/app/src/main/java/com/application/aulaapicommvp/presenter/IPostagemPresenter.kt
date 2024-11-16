package com.application.aulaapicommvp.presenter

import com.application.aulaapicommvp.model.Postagem

interface IPostagemPresenter {
    fun exibirPostagens( lista: List<Postagem>  )
    fun carregando( exibirCarregando: Boolean )
}