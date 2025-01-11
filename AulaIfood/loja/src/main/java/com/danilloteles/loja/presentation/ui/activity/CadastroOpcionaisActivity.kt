package com.danilloteles.loja.presentation.ui.activity

import android.Manifest
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.esconderTeclado
import com.danilloteles.core.exibirMensagem
import com.danilloteles.loja.R
import com.danilloteles.loja.databinding.ActivityCadastroOpcionaisBinding
import com.danilloteles.loja.domain.model.Opcional
import com.danilloteles.loja.domain.model.Produto
import com.danilloteles.loja.domain.model.UploadStorage
import com.danilloteles.loja.presentation.ui.adapter.OpcionaisAdapter
import com.danilloteles.loja.presentation.viewmodel.OpcionalViewModel
import com.danilloteles.core.util.ConstantesFirebase
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jamiltondamasceno.core.adicionarMascaraMoeda
import com.jamiltondamasceno.core.formatarComoMoeda
import com.jamiltondamasceno.core.moedaToDouble
import com.permissionx.guolindev.PermissionX
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID

@AndroidEntryPoint
class CadastroOpcionaisActivity : AppCompatActivity() {

   private val binding by lazy {
      ActivityCadastroOpcionaisBinding.inflate(layoutInflater)
   }
   private val opcionais = listOf(
      Opcional(
         "",
         "",
         "Molho",
         "Composto por um hambúrguer de carne 100% bovina",
         3.00,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030416_204bpeti9ba.png"
      ),
      Opcional(
         "",
         "",
         "Cebola",
         "Composto por um hambúrguer de carne 100% bovina",
         2.00,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202410150806_uc1qzud8yyf.png"
      ),
      Opcional(
         "",
         "",
         "Carne 100% Bovina",
         "Composto por um hambúrguer de carne 100% bovina",
         9.00,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202410150806_3lrri4k5ijb.png"
      ),
      Opcional(
         "",
         "",
         "Maionese",
         "Composto por um hambúrguer de carne 100% bovina",
         3.00,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202501030414_0iotkucm6s7u.png"
      ),
      Opcional(
         "",
         "",
         "Queijo",
         "Composto por um hambúrguer de carne 100% bovina",
         9.00,
         "https://static.ifood-static.com.br/image/upload/t_medium/pratos/e35a1e98-0584-4315-afcb-ad6c621ce28a/202410150807_mruwic5r0ye.png"
      )
   )
   private lateinit var opcionaisAdapter: OpcionaisAdapter
   private val opcionalViewModel: OpcionalViewModel by viewModels()
   private val alertaCarregamento by lazy {
      AlertaCarregamento(this)
   }
   private var produto: Produto? = null
   private var uriOpcional: Uri? = null

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )
      inicializar()
      solicitarPermissoes()
   }

   override fun onStart() {
      super.onStart()
      produto?.let { produto ->
         recuperarOpcionais( produto.id )
      }
   }

   private fun inicializar() {
      inicializarDadosProduto()
      inicializarToolbar()
      inicializarOpcionais()
      inicializarEventosClique()
   }

   private fun inicializarEventosClique() {
      with( binding ){
         btnSelecionarImagemOpcional.setOnClickListener {
            selecionarImagemOpcional.launch(
               PickVisualMediaRequest(
                  ActivityResultContracts.PickVisualMedia.ImageOnly
               )
            )
         }

         btnSalvarOpcional.setOnClickListener { view ->

            view.esconderTeclado()
            //Remover Focus
            editNomeOpcional.clearFocus()
            editPrecoOpcional.clearFocus()
            editDescricaoOpcional.clearFocus()

            val nome = editNomeOpcional.text.toString()
            val preco = editPrecoOpcional.moedaToDouble()
            val descricao = editDescricaoOpcional.text.toString()

            produto?.let { produto ->
               val opcional = Opcional(
                  idProduto = produto.id, nome = nome,
                  descricao = descricao, preco = preco
               )

               val resultadoValidacao = validaDadosOpcional( opcional )
               if ( resultadoValidacao ) {
                  salvarOpcional( opcional )
               }else{
                  exibirMensagem("Prencha os campos do produto")
               }
            }
         }
      }
   }

   private fun validaDadosOpcional( opcional: Opcional ): Boolean {
      if ( opcional.nome.isEmpty() ) return false
      if ( opcional.descricao.isEmpty() ) return false
      if ( opcional.preco == 0.0 ) return false
      if ( uriOpcional == null ) return false
      return true
   }

   private fun salvarOpcional( opcional: Opcional ) {

      uriOpcional?.let { uri ->
         opcionalViewModel.salvar(
            UploadStorage(
               ConstantesFirebase.STORAGE_OPCIONAIS,
               UUID.randomUUID().toString(),
               uri
            ), opcional
         ) { uiStatus ->
            when ( uiStatus ) {
               is UIStatus.Erro -> {
                  alertaCarregamento.fechar()
                  exibirMensagem( uiStatus.erro )
               }
               is UIStatus.Sucesso -> {
                  alertaCarregamento.fechar()
                  limparDadosOpcionais()
                  produto?.let { produto ->
                     recuperarOpcionais( produto.id )
                  }
                  exibirMensagem("Opcional salvo com sucesso!")
               }
               is UIStatus.Carregando -> {
                  alertaCarregamento.exibir("Salvando dados do Opcional")
               }
            }
         }
      }

   }

   private fun recuperarOpcionais( idProduto: String ) {

      opcionalViewModel.listar( idProduto ){ uiStatus ->
         when ( uiStatus ) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               exibirMensagem( uiStatus.erro )
            }
            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               val listaOpcionais = uiStatus.dados
               opcionaisAdapter.adicionarItens( listaOpcionais )
            }
            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Salvando dados do Opcional")
            }
         }
      }
   }

   private fun limparDadosOpcionais() {
      uriOpcional = null
      with( binding ){
         editNomeOpcional.setText("")
         editPrecoOpcional.setText("")
         editDescricaoOpcional.setText("")
         imageCapaOpcional.setImageDrawable(
            ContextCompat.getDrawable(
               applicationContext, R.drawable.nao_disponivel
            )
         )
      }
   }

   private val selecionarImagemOpcional = registerForActivityResult(
      ActivityResultContracts.PickVisualMedia()
   ) { uri ->
      if( uri != null ) {
         binding.imageCapaOpcional.setImageURI( uri )
         uriOpcional = uri
      } else {
         exibirMensagem("Nenhuma imagem foi selecionada para o produto!")
      }
   }

   private fun inicializarDadosProduto() {
      binding.editPrecoOpcional.adicionarMascaraMoeda()
      val bundle = intent.extras
      produto = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
         bundle?.getParcelable("produto", Produto::class.java)
      } else {
         bundle?.getParcelable("produto")
      }

      produto?.let { produto ->
         binding.textDadosProduto.text = "${produto.nome} - ${produto.preco.formatarComoMoeda()}"
      }

   }

   private fun inicializarOpcionais() {

      with( binding ){
         opcionaisAdapter = OpcionaisAdapter{ opcional ->
            configurarExclusao( opcional )
         }
         opcionaisAdapter.adicionarItens( opcionais )
         rvOpcionais.adapter = opcionaisAdapter
         rvOpcionais.layoutManager = LinearLayoutManager(
            applicationContext, RecyclerView.VERTICAL, false
         )
      }

   }

   private fun configurarExclusao(opcional: Opcional) {
      MaterialAlertDialogBuilder(this)
         .setTitle("Deseja realmente excluir o opcional?")
         .setMessage("[${opcional.nome}] - ${opcional.preco.formatarComoMoeda()}")
         .setNegativeButton("Cancelar"){ dialog, _ ->
            dialog.dismiss()
         }
         .setPositiveButton("Confirmar exclusão"){ dialog, _ ->
            removerOpcional( opcional )
            dialog.dismiss()
         }
         .show()
   }

   private fun removerOpcional(opcional: Opcional) {

      opcionalViewModel.remover( opcional ) { uiStatus ->
         when (uiStatus) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               exibirMensagem( uiStatus.erro )
            }
            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               recuperarOpcionais( opcional.idProduto )
            }
            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Excluindo Opcional")
            }
         }
      }

   }

   private fun inicializarToolbar() {
      val toolbar = binding.includeTbOpcionais.tbPrincipalLoja
      setSupportActionBar(toolbar)

      supportActionBar?.apply {
         title = "Adicionar opcionais"
         setDisplayHomeAsUpEnabled(true)
      }
   }

   private fun solicitarPermissoes() {

      val listaPermissoes = mutableListOf<String>()

      //Solicitar permissões de acordo com versões
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
         listaPermissoes.add(Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED) //>=34
         listaPermissoes.add(READ_MEDIA_IMAGES) //>=33
      } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
         listaPermissoes.add(READ_MEDIA_IMAGES) //>=33
      } else {
         listaPermissoes.add(Manifest.permission.READ_EXTERNAL_STORAGE) //<=32
      }

      PermissionX.init(this)
         .permissions(listaPermissoes)
         .explainReasonBeforeRequest()
         .onExplainRequestReason { scope, permissoesNegadas ->
            scope.showRequestReasonDialog(
               permissoesNegadas,
               "As permissões são necessárias para configurar imagem para Opcional",
               "Conceder permissões",
               "Cancelar"
            )
         }
         .onForwardToSettings { scope, permissoesNegadas ->
            scope.showForwardToSettingsDialog(
               permissoesNegadas,
               "Voçê precisa conceder permissões manualmente em configurações",
               "Abrir configurações",
               "Cancelar"
            )
         }
         .request { todasConcedidas, permissoesConcedidas, permissoesNegadas ->
            if (!todasConcedidas) {
               exibirMensagem("Permissões negadas, não é possível configurar imagem do opcional")
            }
         }
   }
}