package com.danilloteles.aularoomdatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aularoomdatabase.data.BandoDados
import com.danilloteles.aularoomdatabase.data.dao.EnderecoDAO
import com.danilloteles.aularoomdatabase.data.dao.UsuarioDAO
import com.danilloteles.aularoomdatabase.data.model.Endereco
import com.danilloteles.aularoomdatabase.data.model.Usuario
import com.danilloteles.aularoomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }
    private lateinit var bancoDados: BandoDados
    private lateinit var usuarioDAO: UsuarioDAO
    private lateinit var enderecoDAO: EnderecoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        bancoDados = BandoDados.recuperarInstanciaRoom( this )
        //usuarioDAO = bancoDados.recuperarUsuarioDao()
        usuarioDAO = bancoDados.usuarioDAO
        enderecoDAO = bancoDados.enderecoDAO


        binding.btnSalvar.setOnClickListener {

            val nome = binding.editNome.text.toString()
            val usuario = Usuario(
                0,
                "j@gmail.com",
                nome,
                "1234",
                20,
                30.5,
                Date(),
            )
            val endereco = Endereco(
                0, "Rua tal, número 20"
            )
            CoroutineScope(Dispatchers.IO).launch {
                usuarioDAO.salvar( usuario )
                enderecoDAO.salvar( endereco )
            }

        }
        binding.btnRemover.setOnClickListener {
            val usuario = Usuario(
                3,
                "j@gmail.com",
                "Maria",
                "1234",
                20,
                30.5,
                Date()
            )
            CoroutineScope(Dispatchers.IO).launch {
                usuarioDAO.remover( usuario )
            }
        }
        binding.btnAtualizar.setOnClickListener {

            val nome = binding.editNome.text.toString()
            val usuario = Usuario(
                2,
                "j@gmail.com",
                nome,
                "1234",
                20,
                30.5,
                Date(),
            )
            CoroutineScope(Dispatchers.IO).launch {
                usuarioDAO.atualizar( usuario )
            }
        }
        binding.btnListar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val listaUsuarios = usuarioDAO.listar()
                //val textoPesquisa = binding.editNome.text.toString()
                //val listaUsuarios = usuarioDAO.filtrar( textoPesquisa )
                var textoUsuarios = ""
                listaUsuarios.forEach { usuario ->

                    val formatador = SimpleDateFormat("dd/MM/yyyy hh:mm")
                    val dataFormatada = formatador.format( usuario.data )

                    textoUsuarios += "${usuario.id}) ${usuario.nomeSobrenome} dt: $dataFormatada \n"
                }
                withContext( Dispatchers.Main ){
                    binding.textListaUsuarios.text = textoUsuarios
                }
            }
        }
        binding.btnFiltrar.setOnClickListener {
            CoroutineScope( Dispatchers.IO ).launch {
                val textoPesquisa = binding.editNome.text.toString()
                val listaUsuarios = usuarioDAO.filtrar( textoPesquisa )
                var textoUsuarios = ""
                listaUsuarios.forEach { usuario ->

                    val formatador = SimpleDateFormat("dd/MM/yyyy hh:mm")
                    val dataFormatada = formatador.format( usuario.data )

                    textoUsuarios += "${usuario.nomeSobrenome} Data: $dataFormatada \n"
                }
                withContext( Dispatchers.Main ){
                    binding.textListaUsuarios.text = textoUsuarios
                }
            }
        }

    }
}