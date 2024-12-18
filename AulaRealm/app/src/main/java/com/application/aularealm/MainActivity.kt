package com.application.aularealm

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.application.aularealm.database.DatabaseRealm
import com.application.aularealm.databinding.ActivityMainBinding
import com.application.aularealm.model.Usuario
import io.realm.kotlin.types.ObjectId

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val realm by lazy {
        DatabaseRealm()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener {

            val nomeRecuperado = binding.editNome.text.toString()

            val usuario = Usuario().apply {
                nome = nomeRecuperado
                idade = 10
            }
            realm.salvar( usuario )

        }

        binding.btnListar.setOnClickListener {

            val lista = realm.listar()

            var textoLista = ""
            lista.forEach { usuario ->
                textoLista += "${usuario.nome} - idade: ${usuario.idade} \n"
                Log.i("info_realm", "id: ${usuario.id} - ${usuario.nome}")
            }
            binding.textResultado.text = textoLista
        }

        binding.btnRemover.setOnClickListener {

            //6715af69b36cc749d3222495
            val id = ObjectId.from("6715af69b36cc749d3222495")
            realm.remover( id )
        }

        binding.btnAtualizar.setOnClickListener {

            val nomeRecuperado = binding.editNome.text.toString()
            val idSelecionado = ObjectId.from("6715ad20417e6e254c4facca")
            val usuario = Usuario().apply {
                id = idSelecionado
                nome = nomeRecuperado
                idade = 40
            }
            realm.atualizar( usuario )

        }

    }
}