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
                radioButton()
            }

            /*rbMasculino.setOnClickListener {  }
            rbMasculino.setOnCheckedChangeListener { _, isChecked -> }*/

            /*cbConfirmacao.setOnCheckedChangeListener { _, isChecked ->
                val resultado = if ( isChecked ) "Sim" else "Não"
                textResultado.text = "valor selecionado: $resultado"
            }*/

            /*cbConfirmacao.setOnClickListener {
                val selecionado = binding.cbConfirmacao.isChecked // verificar se está marcado ou não
                val resultado = if ( selecionado ) "Sim" else "Não"
                textResultado.text = "valor selecionado: $resultado"
            }*/

        }

    }

    private fun radioButton() {

        val masculino = binding.rbMasculino.isChecked
        val feminino = binding.rbFeminino.isChecked
        //binding.textResultado.text = if (masculino) "Masculino" else if (feminino) "Feminino" else "Nenhum"

        val itemSelecionado = binding.rgSexo.checkedRadioButtonId
        binding.textResultado.text = when ( itemSelecionado ){
            R.id.rbMasculino -> "Masculino"
            R.id.rbFeminino -> "Feminino"
            else -> "Nada selecionado"
        }

        binding.rgSexo.clearCheck()// limpar o radio button


        /*if ( selecionadoMasculino ){
            binding.textResultado.text = "Masculino"
        }else if ( binding.rbFeminino.isChecked ){
            binding.textResultado.text = "Feminino"
        }else{

        }*/

    }

    private fun checkbox() {
        val selecionado = binding.cbConfirmacao.isChecked // verificar se está marcado ou não
        val resultado = if ( selecionado ) "Sim" else "Não"
        binding.textResultado.text = "valor selecionado: $resultado"
    }
}