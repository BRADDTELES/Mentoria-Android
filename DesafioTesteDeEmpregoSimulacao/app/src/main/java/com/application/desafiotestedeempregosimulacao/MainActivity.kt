package com.application.desafiotestedeempregosimulacao

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.desafiotestedeempregosimulacao.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var galeriaAdapter: GaleriaAdapter // Adaptador para a RecyclerView para carregar/atualizar dados da Web - galeria de imagens

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        galeriaAdapter = GaleriaAdapter()
        galeriaAdapter.adicionarLista(
            listOf(
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000",
                "https://img.freepik.com/premium-photo/nice-cute-kittens-digital-realistic-illustration-front-face-baby-cat-with-nice-lighting_510654-340.jpg?w=2000"
            )
        )
        binding.rvGaleria.adapter = galeriaAdapter // galeriaAdapter é do tipo adapter
        binding.rvGaleria.layoutManager = GridLayoutManager(
            this, // contexto
            3, // quantidade de colunas
            RecyclerView.VERTICAL, // orientação da lista (vertical)
            false // reverseLayout (false)
        )

    }
}