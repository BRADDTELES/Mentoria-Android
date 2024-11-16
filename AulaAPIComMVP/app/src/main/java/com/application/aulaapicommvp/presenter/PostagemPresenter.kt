package com.application.aulaapicommvp.presenter

import com.application.aulaapicommvp.api.RetrofitService
import com.application.aulaapicommvp.model.Postagem
import com.application.aulaapicommvp.model.PostagemAPI
import retrofit2.Response

class PostagemPresenter {

    fun recuperarPostagens(){

        val postagemAPI = PostagemAPI()
        val postagens = postagemAPI.recuperarPostagens()

    }
}