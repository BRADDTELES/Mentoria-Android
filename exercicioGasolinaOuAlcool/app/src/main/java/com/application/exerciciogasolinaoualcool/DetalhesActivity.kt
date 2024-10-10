package com.application.exerciciogasolinaoualcool

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalhesActivity : AppCompatActivity() {

    private lateinit var textGasolina: TextView
    private lateinit var textAlcool: TextView
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        textGasolina = findViewById(R.id.text_gasolina)
        textAlcool = findViewById(R.id.text_alcool)
        textResultado = findViewById(R.id.text_resultado)

        val bundle = intent.extras
        if ( bundle != null ) {

            val precoAlcool = bundle.getDouble("alcool")
            val precoGasolina = bundle.getDouble("gasolina")

            textGasolina.text = "Preço Gasolina: %.2f".format(precoGasolina)
            textAlcool.text = "Preço Alcool: %.2f".format(precoAlcool)

            val resultado = precoAlcool / precoGasolina
            if ( resultado >= 0.7 ){
                textResultado.text = "Gasolina"
            }else{
                textResultado.text = "Álcool"
            }
        }
    }
}