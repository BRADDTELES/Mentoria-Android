package com.application.aulacalculadoraimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnCalcular: Button
    private lateinit var editPeso: EditText
    private lateinit var editAltura: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnCalcular = findViewById(R.id.btn_calcular)
        editPeso = findViewById(R.id.edit_peso)
        editAltura = findViewById(R.id.edit_altura)

        /* Clique de alteração de Tela */
        btnCalcular.setOnClickListener {
            /* comando para alterar a tela */
            val intent = Intent(this, ResultadoActivity::class.java)

            // Recuperar valores dos campos de entrada no formatado String
            val peso = editPeso.text.toString()
            val altura = editAltura.text.toString()

            // Validar se os campos estão preenchidos, caso esteja Vazios
            if ( peso.isNotEmpty() && altura.isNotEmpty() ){
                // Converter dados Strings para Double e enviar para a tela ResultadoActivity
                intent.putExtra("peso", peso.toDouble())
                intent.putExtra("altura", altura.toDouble())
            }

            startActivity( intent )

        }

    }
}