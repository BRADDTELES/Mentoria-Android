package com.application.aulacomponentesinterfaceviewbinding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.application.aulacomponentesinterfaceviewbinding.databinding.ActivityFormularioBinding

class FormularioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioBinding.inflate(layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with( binding ){

            btnEnviar.setOnClickListener {
                //checkbox()
            }

            cbConfirmacao.setOnCheckedChangeListener { _, isChecked ->
                val resultado = if ( isChecked ) "Sim" else "Não"
                textResultado.text = "valor selecionado: $resultado"
            }

            /*cbConfirmacao.setOnClickListener {
                val selecionado = binding.cbConfirmacao.isChecked // verificar se está marcado ou não
                val resultado = if ( selecionado ) "Sim" else "Não"
                textResultado.text = "valor selecionado: $resultado"
            }*/

        }

    }

    private fun checkbox() {
        val selecionado = binding.cbConfirmacao.isChecked // verificar se está marcado ou não
        val resultado = if ( selecionado ) "Sim" else "Não"
        binding.textResultado.text = "valor selecionado: $resultado"
    }
}