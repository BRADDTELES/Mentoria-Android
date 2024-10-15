package com.application.aulacomponenteslistagemcolecoes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MensagemAdapter(
    private val clique: (String) -> Unit
) : Adapter<MensagemAdapter.MensagemViewHolder>() {

    private var listaMensagens = mutableListOf<Mensagem>()

    fun executarOperacao(){

        /*listaMensagens.add(
            //0, // irá adicionar no inicio da lista
            Mensagem("Nova Pedro", "teste", "17:12")
        )
        //notifyItemInserted( 0 ) // irá adicionar no inicio da lista
        //notifyItemInserted( listaMensagens.size ) // irá adicionar no final da lista
        listaMensagens.add(
            Mensagem("Nova Maria", "teste Maria", "13:12")
        )
        listaMensagens.add(
            Mensagem("Nova João", "teste João", "11:12")
        )*/
        //notifyItemRangeInserted(2, 3)

        /*listaMensagens[0] = Mensagem("jamilton DAMASCENO", "Deu tudo certo?", "11:45")
        notifyItemChanged(0) // atualiza apenas um item na posição 0*/

        /*listaMensagens[0] = Mensagem("jamilton DAMASCENO", "Deu tudo certo?", "11:45")
        listaMensagens[1] = Mensagem("Ana", "Olá, tudo bem?", "11:45")
        notifyItemRangeChanged(0, 2)// atualiza os dois itens apartir da posição 0*/

        listaMensagens.removeAt(1)
        //notifyItemRemoved(1)// remove apenas um item na posição 1
        listaMensagens.removeAt(2)// remove apenas um item na posição 2
        notifyItemRangeRemoved(1,2)// remove os dois itens apartir da posição 1

    }

    fun atualizarListaDados( lista: MutableList<Mensagem> ){
        //listaMensagens.addAll( lista )
        listaMensagens = lista
        //notifyDataSetChanged()
    }

    inner class MensagemViewHolder(
        val itemView: View
    ) : ViewHolder( itemView ){

        /*val textNome: TextView = itemView.findViewById(R.id.text_nome)
        val textUltima: TextView = itemView.findViewById(R.id.text_ultima)*/

        val textNome: TextView = itemView.findViewById(R.id.text_card_nome)
        val textUltima: TextView = itemView.findViewById(R.id.text_card_ultima)
        val imagePerfil: ImageView = itemView.findViewById(R.id.image_card_perfil)
        val cardView: CardView = itemView.findViewById(R.id.card_view_layout)
        //val textHorario: TextView = itemView.findViewById(R.id.text_horario)

        fun bind(mensagem: Mensagem){//Conectar com a interface
            textNome.text = mensagem.nome
            textUltima.text = mensagem.ultima
            //textHorario.text = mensagem.horario

            //Aplicar eventos de clique (ao clicar na imagem)
            //val context = cardView.context
            cardView.setOnClickListener {
                clique( mensagem.nome )
            }
        }

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
    override fun onBindViewHolder(holder: MensagemViewHolder, position: Int) {
        val mensagem = listaMensagens[position]
        holder.bind( mensagem )
    }
    //getItemCount -> Recuperar a quantidade de itens
    override fun getItemCount(): Int {
        return listaMensagens.size
    }
}