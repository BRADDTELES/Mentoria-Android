package com.jamiltondamasceno.projetonetflixapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamiltondamasceno.projetonetflixapi.api.RetrofitService
import com.jamiltondamasceno.projetonetflixapi.databinding.ItemFilmeBinding
import com.jamiltondamasceno.projetonetflixapi.model.Filme
import com.squareup.picasso.Picasso

class FilmeAdapter(
    val onClick: (Filme) -> Unit
) : RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    private var listaFilmes = mutableListOf<Filme>() // Criando uma lista vazia

    // Método para adicionar a lista de filmes
    fun adicionarLista( lista: List<Filme> ){
        this.listaFilmes.addAll( lista )//20 + 20 + 20 + 20
        notifyDataSetChanged() // Notificar o adapter que a lista foi alterada
    }

    inner class FilmeViewHolder(val binding: ItemFilmeBinding)
        : RecyclerView.ViewHolder(binding.root) {

            // Método para vincular os dados do filme com a view
            fun bind(filme: Filme){ // Recebe o filme como parâmetro

                val nomeFilme = filme.backdrop_path // Nome do arquivo do filme
                val tamanhoFilme = "w300" // Tamanho da Imagem do filme
                val urlBase = RetrofitService.BASE_URL_IMAGEM

                val urlFilme = urlBase + tamanhoFilme + nomeFilme

                Picasso.get()
                    .load(urlFilme)
                    .into( binding.imgItemFilme )

                binding.textTitulo.text = filme.title // Pegando o dado do filme titulo e exibindo na tela em textTitulo
                binding.clItem.setOnClickListener {
                    onClick( filme )
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {

        // Inflar o layout do item do filme e criar o ViewHolder
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding  = ItemFilmeBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return FilmeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        val filme = listaFilmes[position] // Obter o filme na posição atual
        holder.bind( filme ) // Vincular os dados do filme com a view
    }

    override fun getItemCount(): Int {
        return listaFilmes.size // Retornar o tamanho da lista
    }


}