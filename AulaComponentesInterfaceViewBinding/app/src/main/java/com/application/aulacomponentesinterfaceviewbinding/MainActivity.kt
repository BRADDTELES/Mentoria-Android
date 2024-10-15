package com.application.aulacomponentesinterfaceviewbinding

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.application.aulacomponentesinterfaceviewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //private lateinit var btnClique: Button
    //private lateinit var binding: ActivityMainBinding
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView( binding.root )

        //Button -> Button, TextView, View
        //TextView -> TextView, View
        //LinearLayout -> LinearLayout, ViewGroup, View
        //RecyclerView -> RecyclerView, ViewGroup, View

        with(binding){
            btnClique?.setOnClickListener {  }
            btnExecutar?.setOnClickListener {  }
        }

        /*btnClique = findViewById(R.id.btn_clique)
        btnClique.setOnClickListener {
            Toast.makeText(this, "Olá ", Toast.LENGTH_SHORT).show()
        }*/
    }
}