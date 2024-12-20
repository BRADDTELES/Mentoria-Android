package com.app.aularecyclerviewdozero

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ConversasAdapter(
    private val listaConversas: List<Conversa>
) : Adapter<ConversasAdapter.ConversaViewHolder>() {

    //View Holder -> Dono da visualização, o responsável pela visualização
    inner class ConversaViewHolder( itemView: View ) : ViewHolder( itemView ) {

        val textNome : TextView = itemView.findViewById(R.id.text_nome)// 1º forma, diferente de fazer
        val imagePerfil = itemView.findViewById<ImageView>(R.id.image_perfil)// 2º forma, diferente de fazer
        //Apontar para Imagem e Texto

    }

    //Executado para cada criação de View, converter XML para Objeto do tipo View
    // Chamado poucas vezes: se tenho 1000 itens, ex: vai ser chamado 40 vezes
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversaViewHolder {

        //Inflar o Layout (layoutInflater), Converter XML para Objeto View
        /*val layoutInflater = LayoutInflater.from( parent.context )

        val view = layoutInflater.inflate(
            R.layout.item_conversa, parent, false
        )*/
        val view = LayoutInflater
            .from( parent.context )
            .inflate( R.layout.item_conversa, parent, false)

        return ConversaViewHolder( view )//View

    }

    //1) Verifica a quantidade de itens a serem criados
    override fun getItemCount(): Int {//recupera quantidade de itens na lista a ser recebida
        return listaConversas.size
    }

    //Conecta os dados com a View
    override fun onBindViewHolder(conversaViewHolder: ConversaViewHolder, position: Int) {

        val conversa = listaConversas[ position ]
        conversaViewHolder.textNome.text = conversa.nome

    }
}