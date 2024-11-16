package com.application.aulaapicommvp.presenter

import android.util.Log
import com.application.aulaapicommvp.api.RetrofitService
import com.application.aulaapicommvp.model.Postagem
import com.application.aulaapicommvp.model.PostagemAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Response

class PostagemPresenter(
    private val postagemAPI: PostagemAPI
) {

    private val coroutine = CoroutineScope(Dispatchers.IO)

    fun recuperarPostagens(){

        coroutine.launch {
            val postagens = postagemAPI.recuperarPostagens()
            Log.i("resultado_api", "lista: $postagens")
        }

    }

    fun onDestroy(){
        coroutine.cancel()
    }
}