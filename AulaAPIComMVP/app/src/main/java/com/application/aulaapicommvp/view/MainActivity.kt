package com.application.aulaapicommvp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.application.aulaapicommvp.databinding.ActivityMainBinding
import com.application.aulaapicommvp.model.PostagemAPI
import com.application.aulaapicommvp.presenter.PostagemPresenter

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var postagemPresenter: PostagemPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        val postagemAPI = PostagemAPI()
        postagemPresenter = PostagemPresenter( postagemAPI )

    }

    override fun onStart() {
        super.onStart()
        postagemPresenter.recuperarPostagens()
    }

    override fun onDestroy() {
        super.onDestroy()
        postagemPresenter.onDestroy()
    }
}