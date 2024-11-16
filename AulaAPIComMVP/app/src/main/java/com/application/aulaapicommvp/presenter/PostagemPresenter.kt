package com.application.aulaapicommvp.presenter

import android.util.Log
import com.application.aulaapicommvp.api.RetrofitService
import com.application.aulaapicommvp.model.Postagem
import com.application.aulaapicommvp.model.PostagemAPI
import com.application.aulaapicommvp.view.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostagemPresenter(
    private val view: IPostagemPresenter,
    private val postagemAPI: PostagemAPI
) {// O que vai ser exibido

    private val coroutine = CoroutineScope(Dispatchers.IO)

    fun recuperarPostagens(){
        view.carregando(true)
        coroutine.launch {
            delay(5000)
            val postagens = postagemAPI.recuperarPostagens()
            //Log.i("resultado_api", "lista: $postagens")
            withContext(Dispatchers.Main){
                view.exibirPostagens( postagens )
                view.carregando(false)
            }
        }
    }

    fun onDestroy(){
        coroutine.cancel()
    }
}