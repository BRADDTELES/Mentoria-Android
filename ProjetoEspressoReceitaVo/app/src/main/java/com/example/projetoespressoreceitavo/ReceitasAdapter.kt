package com.example.projetoespressoreceitavo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.projetoespressoreceitavo.databinding.ItemReceitasBinding

class ReceitasAdapter(
    val cliqueBotao: (Receita)-> Unit
) : Adapter<ReceitasAdapter.ReceitaViewHolder>() {

    private var listaReceitas = listOf<Receita>()

    fun configurarLista(lista: List<Receita>){
        listaReceitas = lista
        notifyDataSetChanged()
    }

    inner class ReceitaViewHolder(
        private val binding: ItemReceitasBinding
    ) : ViewHolder( binding.root ) {//ReceitaViewHolder, ViewHolder

        fun bind( receita: Receita, posicao: Int ){

            binding.textTitulo.text = "${receita.titulo} ($posicao)"
            binding.textTempo.text = receita.tempo

            binding.imgReceita.setImageDrawable(
                ContextCompat.getDrawable( binding.clItem.context , receita.resIdImagem )
            )

            //Evento de clique
            binding.clItem.setOnClickListener {
                cliqueBotao( receita )
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitaViewHolder {

        val inflater = LayoutInflater
            .from( parent.context )

        val view = ItemReceitasBinding.inflate( inflater )

        return ReceitaViewHolder( view )

    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {
        val receita = listaReceitas[position]
        holder.bind( receita, position )
    }

    override fun getItemCount(): Int {
        return listaReceitas.size
    }

}