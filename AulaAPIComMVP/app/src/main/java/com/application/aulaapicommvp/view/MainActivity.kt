package com.application.aulaapicommvp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.application.aulaapicommvp.databinding.ActivityMainBinding
import com.application.aulaapicommvp.model.Postagem
import com.application.aulaapicommvp.model.PostagemAPI
import com.application.aulaapicommvp.presenter.IPostagemPresenter
import com.application.aulaapicommvp.presenter.PostagemPresenter

class MainActivity : AppCompatActivity(), IPostagemPresenter {// Como vai ser exibido

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var postagemPresenter: PostagemPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        val postagemAPI = PostagemAPI()
        postagemPresenter = PostagemPresenter( this, postagemAPI )

    }

    override fun onStart() {
        super.onStart()
        postagemPresenter.recuperarPostagens()
    }

    override fun onDestroy() {
        super.onDestroy()
        postagemPresenter.onDestroy()
    }

    override fun exibirPostagens(lista: List<Postagem>) {

        var textoResultado = ""
        lista.forEach { postagem ->
            textoResultado += "${postagem.id}) ${postagem.title} \n"
        }
        binding.textResultado.text = textoResultado
    }

    override fun carregando(exibirCarregando: Boolean) {
        if ( exibirCarregando ){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}