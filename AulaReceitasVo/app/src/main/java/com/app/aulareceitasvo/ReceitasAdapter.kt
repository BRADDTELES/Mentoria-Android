package com.app.aulareceitasvo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ReceitasAdapter(
    val cliqueBotao: () -> Unit
) : Adapter<ReceitasAdapter.ReceitaViewHolder>(){

    private var listaReceitas = listOf<Receita>()

    fun configurarLista(lista: List<Receita>){
        listaReceitas = lista
        notifyDataSetChanged()// Notifica o adaptador para atualizar a lista
    }

    inner class ReceitaViewHolder( itemView: View) : ViewHolder( itemView ){

        private var view: View
        private var textTitulo : TextView
        private var textTempo : TextView
        private var imageReceita : ImageView
        private lateinit var clItem : ConstraintLayout

        init {
            view = itemView
            textTitulo = view.findViewById(R.id.text_titulo)
            textTempo = view.findViewById(R.id.text_tempo)
            imageReceita = view.findViewById(R.id.img_receita)
            clItem = view.findViewById(R.id.cl_item)
        }

        fun bind( receita: Receita ){

            textTitulo.text = receita.titulo
            textTempo.text = receita.tempo

            imageReceita.setImageDrawable(
                ContextCompat.getDrawable(view.context, receita.resIdImagem)
            )

            //Evento de clique
            clItem.setOnClickListener {
                cliqueBotao()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceitaViewHolder {

        val view = LayoutInflater
            .from( parent.context )
            .inflate(R.layout.item_receitas, parent, false)

        return ReceitaViewHolder( view )

    }

    override fun onBindViewHolder(holder: ReceitaViewHolder, position: Int) {
        val receita = listaReceitas[position]
        holder.bind( receita )
    }

    override fun getItemCount(): Int {
        return listaReceitas.size
    }

}