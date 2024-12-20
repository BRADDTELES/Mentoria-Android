package com.app.aulareceitasvo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class DetalhesActivity : AppCompatActivity() {

    private lateinit var imgDetalhe: ImageView
    private lateinit var textTituloDetalhe: TextView
    private lateinit var textTempoDetalhe: TextView
    private lateinit var textReceitaDetalhe: TextView
    private lateinit var btnVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        imgDetalhe = findViewById(R.id.img_detalhe)
        textTituloDetalhe = findViewById(R.id.text_titulo_detalhe)
        textTempoDetalhe = findViewById(R.id.text_tempo_detalhe)
        textReceitaDetalhe = findViewById(R.id.text_receita_detalhe)
        btnVoltar = findViewById(R.id.btn_voltar)

        btnVoltar.setOnClickListener {
            finish()
        }

        val bundle = intent.extras
        if (bundle != null) {

            val receita = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("receita", Receita::class.java)
            } else {
                bundle.getParcelable("receita")
            }

            if (receita != null) {
                imgDetalhe.setImageDrawable(
                    ContextCompat.getDrawable(this, receita.resIdImagem)
                )

                textTituloDetalhe.text = receita.titulo
                textTempoDetalhe.text = receita.tempo

                val listaIngredientes = receita.ingreditentes

                var textoIngredientes = ""
                for ( ingrediente in listaIngredientes ){
                    textoIngredientes += "+ $ingrediente \n"
                }

                textReceitaDetalhe.text = textoIngredientes

            }

        }

    }
}