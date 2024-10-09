package com.application.exerciciogasolinaoualcool

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool: TextInputLayout
    private lateinit var editAlcool: TextInputEditText
    private lateinit var textInputGasolina: TextInputLayout
    private lateinit var editGasolina: TextInputEditText
    private lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textInputAlcool = findViewById(R.id.text_input_alcool)
        editAlcool = findViewById(R.id.edit_alcool)
        textInputGasolina = findViewById(R.id.text_input_gasolina)
        editGasolina = findViewById(R.id.edit_gasolina)

        btnCalcular = findViewById(R.id.btn_calcular)

        btnCalcular.setOnClickListener {

            val alcool = editAlcool.text.toString()
            val gasolina = editGasolina.text.toString()

            if ( alcool.isNotEmpty() && gasolina.isNotEmpty() ){
                // Mudando de tela com o clique, após dados válidados
                val intent = Intent(this, DetalhesActivity::class.java)
                intent.putExtra("alcool", alcool.toDouble())
                intent.putExtra("gasolina", gasolina.toDouble())
                startActivity(intent)
            }else{
                textInputAlcool.error = null
                textInputGasolina.error = null
                if ( alcool.isEmpty() ){
                    textInputAlcool.error = "Opção inválida, digite o preço do álcool"
                    Toast.makeText(this, "Preencha o campo", Toast.LENGTH_SHORT).show()
                }
                if ( gasolina.isEmpty() ){
                    textInputGasolina.error = "Opção inválida, digite o preço da gasolina"
                    Toast.makeText(this, "Preencha o campo", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}