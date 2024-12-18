package com.example.projetoespressoreceitavo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoespressoreceitavo.databinding.ActivityDetalhesBinding
import com.example.projetoespressoreceitavo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val bindig by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    private lateinit var receitasAdapter: ReceitasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( bindig.root )

        val lista = listOf(
            Receita("Escondidinho de camarão", "25 min", R.drawable.carne1,
                listOf("1KG de camaração", "Manteiga", "alho", "cebola")
            ),
            Receita("Panqueca de carne moída", "15 min", R.drawable.carne2,
                listOf("1KG de carne", "Manteiga", "alho")
            ),
            Receita("Rocambole de cane moída", "30 min", R.drawable.carne3,
                listOf("Carne a vontade", "Manteiga", "alho", "cebola")
            ),
            Receita("Escondidinho de carne seca", "45 min", R.drawable.carne4,
                listOf("1KG de carne seca", "Manteiga", "alho", "cebola", "farinha")
            ),
            Receita("Escondidinho de camarão", "25 min", R.drawable.carne1,
                listOf("1KG de camaração", "Manteiga", "alho", "cebola")
            ),
            Receita("Panqueca de carne moída", "15 min", R.drawable.carne2,
                listOf("1KG de carne", "Manteiga", "alho")
            ),
            Receita("Rocambole de cane moída", "30 min", R.drawable.carne3,
                listOf("Carne a vontade", "Manteiga", "alho", "cebola")
            ),
            Receita("Escondidinho de carne seca", "45 min", R.drawable.carne4,
                listOf("1KG de carne seca", "Manteiga", "alho", "cebola", "farinha")
            ),
            Receita("Escondidinho de camarão", "25 min", R.drawable.carne1,
                listOf("1KG de camaração", "Manteiga", "alho", "cebola")
            ),
            Receita("Panqueca de carne moída", "15 min", R.drawable.carne2,
                listOf("1KG de carne", "Manteiga", "alho")
            ),
            Receita("Rocambole de cane moída", "30 min", R.drawable.carne3,
                listOf("Carne a vontade", "Manteiga", "alho", "cebola")
            ),
            Receita("Escondidinho de carne seca", "45 min", R.drawable.carne4,
                listOf("1KG de carne seca", "Manteiga", "alho", "cebola", "farinha")
            ),
            Receita("Escondidinho de carne seca", "45 min", R.drawable.carne4,
                listOf("1KG de carne seca", "Manteiga", "alho", "cebola", "farinha")
            ),
            Receita("Escondidinho de camarão", "25 min", R.drawable.carne1,
                listOf("1KG de camaração", "Manteiga", "alho", "cebola")
            ),
            Receita("Panqueca de carne moída", "15 min", R.drawable.carne2,
                listOf("1KG de carne", "Manteiga", "alho")
            ),
            Receita("Rocambole de cane moída", "30 min", R.drawable.carne3,
                listOf("Carne a vontade", "Manteiga", "alho", "cebola")
            ),
            Receita("Escondidinho de carne seca", "45 min", R.drawable.carne4,
                listOf("1KG de carne seca", "Manteiga", "alho", "cebola", "farinha")
            )
        )

        //Adapter
        //ReceitasAdapter, Adpter
        receitasAdapter = ReceitasAdapter { receita ->
            val intent = Intent(this, DetalhesActivity::class.java)
            intent.putExtra("receita", receita )
            startActivity( intent )
        }
        bindig.rvReceitas.adapter = receitasAdapter
        receitasAdapter.configurarLista( lista )

        //Layout
        bindig.rvReceitas.layoutManager = LinearLayoutManager(this)

    }
}