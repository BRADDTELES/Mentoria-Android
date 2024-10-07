package com.application.aulaalcoolougasolina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool: TextInputLayout
    private lateinit var editAlcool: TextInputEditText

    private lateinit var textInputGasolina: TextInputLayout
    private lateinit var editGasolina: TextInputEditText

    private lateinit var btnCalcular: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarComponentesInterface()
        btnCalcular.setOnClickListener {
            calcularMelhorPreco()
        }
    }

    private fun calcularMelhorPreco() {

        val precoAlcool = editAlcool.text.toString() // Não esqueça de converter depois
        val precoGasolina = editGasolina.text.toString() // Não esqueça de converter depois

        val resultadoValidacao = validarCampos(precoAlcool, precoGasolina)
        if ( resultadoValidacao ){
            val precoAlccolNumero = precoAlcool.toDouble() // Converter to.String para to.Double
            val precoGasolinaNumero = precoGasolina.toDouble() // Converter to.String para to.Double
            val resultado = precoAlccolNumero / precoGasolinaNumero
            if ( resultado >= 0.7 ){
                textResultado.text = "Melhor utilizar Gasolina"
            }else{
                textResultado.text = "Melhor utilizar Álcool"
            }
        }

    }

    private fun validarCampos(pAlcool: String, pGasolina: String): Boolean {

        textInputAlcool.error = null
        textInputGasolina.error = null

        if ( pAlcool.isEmpty() ){
            textInputAlcool.error = "Digite o preço do álcool"
            return false
        }else if ( pGasolina.isEmpty() ){
            textInputGasolina.error = "Digite o preço da gasolina"
            return false
        }

        return true
    }

    private fun inicializarComponentesInterface() {

        textInputAlcool = findViewById(R.id.text_input_alccol)
        editAlcool = findViewById(R.id.edit_alcool)

        textInputGasolina = findViewById(R.id.text_input_gasolina)
        editGasolina = findViewById(R.id.edit_gasolina)

        btnCalcular = findViewById(R.id.btn_calcular)
        textResultado = findViewById(R.id.text_resultado)

    }
}