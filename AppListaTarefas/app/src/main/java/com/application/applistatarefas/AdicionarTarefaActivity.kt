package com.application.applistatarefas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.application.applistatarefas.database.TarefaDAO
import com.application.applistatarefas.databinding.ActivityAdicionarTarefaBinding
import com.application.applistatarefas.databinding.ActivityMainBinding
import com.application.applistatarefas.model.Tarefa

class AdicionarTarefaActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAdicionarTarefaBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        binding.btnSalvar.setOnClickListener {

            if ( binding.editTarefa.text.isNotEmpty() ){
                val descricao = binding.editTarefa.text.toString()
                val tarefa = Tarefa(
                    -1, descricao, "default"
                )

                val tarefaDAO = TarefaDAO(this)
                if ( tarefaDAO.salvar(tarefa) ){
                    Toast.makeText(
                        this,
                        "Tarefa cadastrada com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish() // encerra a activity e volta para a anterior
                }

            }else{
                Toast.makeText(
                    this,
                    "Preencha uma tarefa",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
    }


}