package com.app.aularesumaoviewbindingcomponentesinterface

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.aularesumaoviewbindingcomponentesinterface.databinding.ActivityMainBinding

/*
View Binding (jetpack)
LinearLayout
Button e ImageButton
FloatingActionButton
CheckBox
RadioGroup e RadioButton
Snackbar
* */

class MainActivity : AppCompatActivity() {

    //private lateinit var btnClique: Button
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        //btnClique = findViewById(R.id.btnClique)
        //btnClique.setOnClickListener {  }

        //binding?.btnClique?.setOnClickListener {  }
        with(binding){
            //btnClique.setOnClickListener {  }
            //fabAdicionar.setOnClickListener {  }

            btnClique.setOnClickListener {

                val masculinoMarcado = rbMasculino.isChecked
                if (masculinoMarcado) {
                    textResultado.text = "Masculino"
                }else{
                    textResultado.text = "Feminino"
                }
                radioGroupSexo.clearCheck()

                /*val marcado = cbNotificacao.isChecked//true -> Marcado false -> desmarcado
                if (marcado) {
                    textResultado.text = "Marcado : $marcado"
                }else{
                    textResultado.text = "Desmarcado : $marcado"
                }*/

            }
        }

    }
}