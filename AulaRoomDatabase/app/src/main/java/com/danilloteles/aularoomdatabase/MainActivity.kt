package com.danilloteles.aularoomdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.danilloteles.aularoomdatabase.data.BandoDados
import com.danilloteles.aularoomdatabase.data.dao.ClientePedidoDAO
import com.danilloteles.aularoomdatabase.data.dao.EnderecoDAO
import com.danilloteles.aularoomdatabase.data.dao.PessoaComputadorDAO
import com.danilloteles.aularoomdatabase.data.dao.ProdutoDAO
import com.danilloteles.aularoomdatabase.data.dao.UsuarioDAO
import com.danilloteles.aularoomdatabase.data.entity.Computador
import com.danilloteles.aularoomdatabase.data.entity.Pessoa
import com.danilloteles.aularoomdatabase.data.entity.PessoaComputador
import com.danilloteles.aularoomdatabase.data.entity.Usuario
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
    private lateinit var produtoDAO: ProdutoDAO
    private lateinit var clientePedidoDAO: ClientePedidoDAO
    private lateinit var pessoaComputadorDAO: PessoaComputadorDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )

        bancoDados = BandoDados.recuperarInstanciaRoom( this )
        //usuarioDAO = bancoDados.recuperarUsuarioDao()
        usuarioDAO = bancoDados.usuarioDAO
        enderecoDAO = bancoDados.enderecoDAO
        produtoDAO = bancoDados.produtoDAO
        clientePedidoDAO = bancoDados.clientePedidoDAO
        pessoaComputadorDAO = bancoDados.pessoaComputadorDAO


        binding.btnSalvar.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {

                //Pessoa
                /*val pessoa = Pessoa(
                    0, "Ana", "F"
                )
                val idPessoa = pessoaComputadorDAO.salvarPessoa( pessoa )*/

                //Adicionar computador para pessoa
                /*val computador1 = Computador(
                    0, "Macbook Pro 15", "Apple"
                )
                val computador2 = Computador(
                    0, "Dell Inspiron", "Dell"
                )
                val idComputador1 = pessoaComputadorDAO.salvarComputador( computador1 )
                val idComputador2 = pessoaComputadorDAO.salvarComputador( computador2 )*/

                //Tabela intermediária pessoas_computadores
                val pessoaComputador1 = PessoaComputador(2, 1)
                //val pessoaComputador2 = PessoaComputador(idPessoa, idComputador2)
                pessoaComputadorDAO.salvarPessoaComputador( pessoaComputador1 )
                //pessoaComputadorDAO.salvarPessoaComputador( pessoaComputador2 )

            }

            /*val nome = binding.editNome.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val cliente = Cliente(
                    0,
                    nome,
                    "--"
                )
                val idCliente = clientePedidoDAO.salvarCliente( cliente )

                //Salvar os pedidos
                repeat(3){ numero ->
                    val pedido = Pedido(
                        0,
                        idCliente,
                        "Produto($numero)",
                        200.90 + (150 * numero)
                    )
                    clientePedidoDAO.salvarPedido( pedido )
                }
            }*/

            /*val nome = binding.editNome.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val idProdutoInserido = produtoDAO.salvarProduto(
                    Produto(0, nome, 1200.90)
                )
                produtoDAO.salvarProdutoDetalhe(
                    ProdutoDetalhe(
                        0,
                        idProdutoInserido,
                        "Acer",
                        "Notebook acer descrição completa..."
                    )
                )
            }*/

            /*val usuario = Usuario(
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
                }*/

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
                val listaPessoasComputadores = pessoaComputadorDAO.listarPessoasComComputadores()
                var textoPessoas = ""
                listaPessoasComputadores.forEach { pesssoaComcomputadores ->

                    val idPessoa = pesssoaComcomputadores.pessoa.idPessoa
                    val nome = pesssoaComcomputadores.pessoa.nome
                    textoPessoas += "$idPessoa) $nome \n"

                    val listaComputadores = pesssoaComcomputadores.computadores
                    listaComputadores.forEach { computador ->
                        val idComputador = computador.idComputador
                        val modelo = computador.modelo
                        val marca = computador.marca
                        textoPessoas += "  + $idComputador) $modelo - $marca \n"
                    }

                }
                withContext( Dispatchers.Main ){
                    binding.textListaUsuarios.text = textoPessoas
                }

            }

            /*CoroutineScope(Dispatchers.IO).launch {
                val listaClientesPedidos = clientePedidoDAO.listarClientesComPedidos()
                var textoClientes = ""
                listaClientesPedidos.forEach { clienteComPedidos ->

                    val idCliente = clienteComPedidos.cliente.idCliente
                    val nome = clienteComPedidos.cliente.nome
                    textoClientes += "$idCliente) $nome \n"

                    val listaPedidos = clienteComPedidos.pedidos
                    listaPedidos.forEach { pedido ->
                        val idPedido = pedido.idPedido
                        val produto = pedido.produto
                        val preco = pedido.preco
                        textoClientes += "  + $idPedido) $produto R$ $preco \n"
                    }

                }
                withContext( Dispatchers.Main ){
                    binding.textListaUsuarios.text = textoClientes
                }

            }*/

            /*//Minha Resposta do Desafio
            CoroutineScope(Dispatchers.IO).launch {
                val listaClientePedidos = clientePedidoDAO.listarClientePedidos()
                var textoUsuarios = ""
                listaClientePedidos.forEach { clientePedidos ->

                    val idPedido = clientePedidos.idPedido
                    val idCliente = clientePedidos.idCliente
                    val produto = clientePedidos.produto
                    val preco = clientePedidos.preco

                    textoUsuarios += "$idPedido) $idCliente + $produto + $preco \n"
                }
                withContext( Dispatchers.Main ){
                    binding.textListaUsuarios.text = textoUsuarios
                }
            }*/

            /*CoroutineScope(Dispatchers.IO).launch {
                val listaProdutosDetalhes = produtoDAO.listarProdutosEProdutoDetalhes()
                var textoUsuarios = ""
                listaProdutosDetalhes.forEach { produtoEProdutoDetalhe ->

                    val idProduto = produtoEProdutoDetalhe.produto.idProduto
                    val nome = produtoEProdutoDetalhe.produto.nome

                    val marca = produtoEProdutoDetalhe.produtoDetalhe.marca
                    val descricao = produtoEProdutoDetalhe.produtoDetalhe.descricao

                    textoUsuarios += "$idProduto) $nome + $marca + $descricao \n"
                }
                withContext( Dispatchers.Main ){
                    binding.textListaUsuarios.text = textoUsuarios
                }
            }*/

            /*CoroutineScope(Dispatchers.IO).launch {
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
            }*/
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