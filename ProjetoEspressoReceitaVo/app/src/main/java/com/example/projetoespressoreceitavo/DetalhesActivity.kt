package com.example.projetoespressoreceitavo

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.projetoespressoreceitavo.databinding.ActivityDetalhesBinding
import com.example.projetoespressoreceitavo.databinding.ActivityLoginBinding

class DetalhesActivity : AppCompatActivity() {

    private val bindig by lazy {
        ActivityDetalhesBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( bindig.root )

        bindig.btnVoltar.setOnClickListener {
            finish()
        }

        val bundle = intent.extras
        if( bundle != null ){

            val receita = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("receita", Receita::class.java)
            } else {
                bundle.getParcelable("receita")
            }

            if( receita != null ){

                bindig.imgDetalhe.setImageDrawable(
                    ContextCompat.getDrawable(this, receita.resIdImagem)
                )

                bindig.textTituloDetalhe.text = receita.titulo
                bindig.textTempoDetalhe.text = receita.tempo

                val listaIngredientes = receita.ingredientes

                var textoIngredientes = ""
                for ( ingrediente in listaIngredientes ){
                    textoIngredientes += "+ $ingrediente \n"
                }

                bindig.textReceitaDetalhe.text = textoIngredientes

            }

        }


    }
}