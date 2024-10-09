package com.application.aulafragment

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.application.aulafragment.fragments.ChamadasFragment
import com.application.aulafragment.fragments.ConversasFragment

class MainActivity : AppCompatActivity() {

    private lateinit var btnMercado: Button
    private lateinit var btnChamadas: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("ciclo_vida", "Activity onCreate")

        btnMercado = findViewById(R.id.btn_mercado)
        btnChamadas = findViewById(R.id.btn_chamadas)

        /*val fragmentManager = supportFragmentManager.beginTransaction()

        //Diversas alterações em fragments
        fragmentManager.add( R.id.fragment_conteudo, ConversasFragment() )

        fragmentManager.commit()*/


        btnMercado.setOnClickListener {
            val conversasFragment = ConversasFragment()

            val bundle = bundleOf(
                "categoria" to "Mercado",
                "usuario" to "Danillo"
            )
            conversasFragment.arguments = bundle

            supportFragmentManager
                .beginTransaction()
                .replace( R.id.fragment_conteudo, conversasFragment )
                .commit()
        }

        btnChamadas.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace( R.id.fragment_conteudo, ChamadasFragment() )
                //.remove( conversasFragment )
                .commit()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo_vida", "Activity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo_vida", "Activity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo_vida", "Activity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo_vida", "Activity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo_vida", "Activity onDestroy")
    }
}