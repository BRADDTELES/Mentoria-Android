package com.application.aulaprojetoarquiteturas.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.application.aulaprojetoarquiteturas.databinding.ActivityUsuarioMvvmactivityBinding
import com.application.aulaprojetoarquiteturas.viewmodel.UsuarioViewModel

class UsuarioMVVMActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUsuarioMvvmactivityBinding.inflate( layoutInflater )
    }
    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        usuarioViewModel = ViewModelProvider(this)[UsuarioViewModel::class]
        usuarioViewModel.usuariosLiveData.observe(this){ usuarios ->

            var resultadoLista = ""
            usuarios.forEach { usuario ->
                resultadoLista += "+ ${usuario.nome}\n"
            }
            binding.textLista.text = resultadoLista
        }

        binding.btnCarregar.setOnClickListener{
            usuarioViewModel.recuperarUsuarios()
        }

    }
}