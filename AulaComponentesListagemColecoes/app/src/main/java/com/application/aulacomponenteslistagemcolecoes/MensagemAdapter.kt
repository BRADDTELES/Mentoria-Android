package com.application.aulacomponenteslistagemcolecoes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MensagemAdapter(
    private val lista: List<Mensagem>
) : Adapter<MensagemAdapter.MensagemViewHolder>() {

    inner class MensagemViewHolder(
        val itemView: View
    ) : ViewHolder( itemView ){

        /*val textNome: TextView = itemView.findViewById(R.id.text_nome)
        val textUltima: TextView = itemView.findViewById(R.id.text_ultima)*/

        val textNome: TextView = itemView.findViewById(R.id.text_card_nome)
        val textUltima: TextView = itemView.findViewById(R.id.text_card_ultima)
        //val textHorario: TextView = itemView.findViewById(R.id.text_horario)

    }
    //Ao Criar o View Holder -> Cria a visualização
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHolder {

        val layoutInflater = LayoutInflater.from(
            parent.context
        )

        /*val itemView = layoutInflater.inflate(
            R.layout.item_lista, parent, false
        )*/

        val itemView = layoutInflater.inflate(
            R.layout.item_cardview, parent, false
        )

        return MensagemViewHolder( itemView )

    }
    // ao vincular os dados para a visualização
    override fun onBindViewHolder(mensagemViewHolder: MensagemViewHolder, position: Int) {

        val mensagem = lista[position]
        mensagemViewHolder.textNome.text = mensagem.nome
        mensagemViewHolder.textUltima.text = mensagem.ultima
        //mensagemViewHolder.textHorario.text = mensagem.horario


    }
    //getItemCount -> Recuperar a quantidade de itens
    override fun getItemCount(): Int {
        return lista.size
    }
}