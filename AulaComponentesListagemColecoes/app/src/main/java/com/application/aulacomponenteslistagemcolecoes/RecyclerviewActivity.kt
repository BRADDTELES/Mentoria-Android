package com.application.aulacomponenteslistagemcolecoes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.application.aulacomponenteslistagemcolecoes.teste.Intent

class RecyclerviewActivity : AppCompatActivity() {

    private lateinit var rvLista: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        val lista = listOf(
            Mensagem("jamilton", "Olá, tudo bem?", "09:45"),
            Mensagem("ana", "Te vi ontem..blablablablablabla, blablablablablabla, blablablablablabla, blablablablablabla, blablablablablabla, blablablablablabla, blablablablablabla, blablablablablabla, blablablablablabla", "00:45"),
            Mensagem("maria", "Não acredito...", "06:03"),
            Mensagem("pedro", "Futebol hoje?", "15:32")
        )

        rvLista = findViewById(R.id.rv_lista)

        //tipo: MensagemAdapter, Adapter
        rvLista.adapter = MensagemAdapter( lista ){ nome ->
            Toast.makeText(this, "Olá $nome", Toast.LENGTH_SHORT).show()
            val intent = android.content.Intent(this, ListViewActivity::class.java)
            intent.putExtra("nome", nome)

            startActivity(
                intent
            )
        }

        //LinearLayoutManager (XML e Código)
        rvLista.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        // Divisor entre os CardView
        /*rvLista.addItemDecoration(
            DividerItemDecoration(
                this,
                RecyclerView.VERTICAL
            )
        )*/

        /*rvLista.layoutManager = GridLayoutManager(
            this,
            2
        )*/

        /*rvLista.layoutManager = StaggeredGridLayoutManager(
            2,
            RecyclerView.VERTICAL
        )*/

    }
}