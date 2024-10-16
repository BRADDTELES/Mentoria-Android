package com.application.aulacomponentesinterfaceviewbinding

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.application.aulacomponentesinterfaceviewbinding.databinding.ActivityFormularioBinding
import com.google.android.material.snackbar.Snackbar

class FormularioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioBinding.inflate(layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with( binding ){

            btnEnviar.setOnClickListener { view ->
                //checkbox()
                //radioButton()
                //switchToggle()
                exibirSnackBar(view)
                //Toast.makeText(this, "Mensagem", Toast.LENGTH_SHORT).show()
                /*Snackbar.make(
                    view,
                    "Alteração feita com sucesso!",
                    Snackbar.LENGTH_LONG
                ).show()*/
            }

            /*toggleAtivo.setOnClickListener {  }
            toggleAtivo.setOnCheckedChangeListener { buttonView, isChecked ->  }*/

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

    private fun exibirSnackBar(view: View) {
        //Toast.makeText(this, "Mensagem", Toast.LENGTH_SHORT).show()
        val snackBar = Snackbar.make(
            view,
            "Alteração feita com sucesso!",
            Snackbar.LENGTH_LONG
        )

        snackBar.setAction("Desfazer"){
            Toast.makeText(this, "Desfeito!", Toast.LENGTH_SHORT).show()
        }

        /*snackBar.setTextColor(
            //resources.getColor(R.color.secondary) // está depreciado
            ContextCompat.getColor(
                this,
                R.color.fundo
            )
        )

        snackBar.setActionTextColor(
            ContextCompat.getColor(
                this,
                android.R.color.holo_red_dark
            )
        )

        snackBar.setBackgroundTint(
            ContextCompat.getColor(
                this,
                android.R.color.holo_orange_dark
            )
        )*/

        snackBar.show()
    }

    private fun switchToggle() {

        val switchMarcado = binding.switchNotificacoes.isChecked
        val toggleMarcado = binding.toggleAtivo.isChecked

        val texto = "Switch: $switchMarcado toggle: $toggleMarcado"
        binding.textResultado.text = texto

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