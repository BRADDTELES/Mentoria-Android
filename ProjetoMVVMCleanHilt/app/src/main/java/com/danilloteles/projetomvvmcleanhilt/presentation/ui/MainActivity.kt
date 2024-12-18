package com.danilloteles.projetomvvmcleanhilt.presentation.ui

import android.R
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.danilloteles.myapplication.databinding.ActivityMainBinding
import com.danilloteles.projetomvvmcleanhilt.presentation.viewmodel.UsuariosViewModel
import dagger.hilt.android.AndroidEntryPoint

//DTO -> Data Transfer Object (Objeto de Transferência de Dados) é como se fosse um Model
//Util -> Classe de utilitários, que pode ser chamada também de Helper
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private val usuariosViewModel: UsuariosViewModel by viewModels()
    //private val usuariosViewModel by viewModels<UsuariosViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        /*usuariosViewModel =
            ViewModelProvider(this)[UsuariosViewModel::class.java]*/

        usuariosViewModel.usuarios.observe(this){ listaUsuarios ->
            var listaResultado = ""
            listaUsuarios.forEach{ usuario ->
                val nome = usuario.nome
                val sobrenome = usuario.sobrenome
                val idade = usuario.idade

                listaResultado += "+) $nome - $sobrenome - $idade \n"
            }
            binding.textResultado.text = listaResultado
        }

    }
}