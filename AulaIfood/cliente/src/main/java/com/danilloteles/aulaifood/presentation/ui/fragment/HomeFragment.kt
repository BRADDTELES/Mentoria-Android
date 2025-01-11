package com.danilloteles.aulaifood.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danilloteles.aulaifood.R
import com.danilloteles.aulaifood.databinding.FragmentHomeBinding
import com.danilloteles.aulaifood.domain.model.FiltroCategoria
import com.danilloteles.aulaifood.presentation.ui.adapter.FiltroCategoriaAdapter
import com.danilloteles.aulaifood.presentation.ui.adapter.LojasAdapter
import com.danilloteles.aulaifood.presentation.viewmodel.LojaViewModel
import com.danilloteles.core.AlertaCarregamento
import com.danilloteles.core.UIStatus
import com.danilloteles.core.exibirMensagem
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

   //private lateinit var categoriaAdapter: CategoriaAdapter
   private lateinit var binding: FragmentHomeBinding
   private lateinit var lojasAdapter: LojasAdapter
   private lateinit var ultimasLojasAdapter: LojasAdapter
   private val listaFiltrosCategoria = listOf(
      FiltroCategoria("Restaurantes", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Restaurantes3_JO1o.png?imwidth=128"),
      FiltroCategoria("Mercados", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Mercados_nlXp.png?imwidth=128"),
      FiltroCategoria("Farmácias", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Farmacia_Nosz.png?imwidth=128"),
      FiltroCategoria("Pet", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Pet_e9dU.png?imwidth=128"),
      FiltroCategoria("Bebidas", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Bebidas_x2Lk.png?imwidth=128"),
      FiltroCategoria("Shopping", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/floreseperfume_zTRv.png?imwidth=128"),
      FiltroCategoria("Gourmet", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Gourmet_RQ4A.png?imwidth=128"),
   )

   private lateinit var alertaCarregamento: AlertaCarregamento
   private val lojaViewModel: LojaViewModel by viewModels()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {

      binding = FragmentHomeBinding.inflate(
         inflater,
         container,
         false
      )

      inicializarFiltros()
      inicializarNotificacoes()
      inicializarFiltrosCategoria()
      inicializarSlider()
      inicializarRecyclerviewLojas()
      inicializarRecyclerviewUltimasLojas()
      /* Visualização do meu RecyclerView
      inicializarComponentesInterfaceCategorias()*/

      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)
      //carregar dados
      listarLojas()
   }

   private fun listarLojas() {

      alertaCarregamento = AlertaCarregamento( requireContext() )
      lojaViewModel.listar{ uiStatus ->
         when (uiStatus) {
            is UIStatus.Erro -> {
               alertaCarregamento.fechar()
               activity?.exibirMensagem(uiStatus.erro)
            }

            is UIStatus.Sucesso -> {
               alertaCarregamento.fechar()
               val lojas = uiStatus.dados
               lojasAdapter.adicionarLista(lojas)
               ultimasLojasAdapter.adicionarLista( lojas )
            }

            is UIStatus.Carregando -> {
               alertaCarregamento.exibir("Carregando dados da loja")
            }
         }
      }
   }

   private fun inicializarFiltros() {

      val ordenacaoChecked = binding.chipFiltroOrdenacao.isCheckable
      val entregaChecked = binding.chipEntregaGatris.isCheckable

      binding.chipFiltroOrdenacao.setOnClickListener{
         val ordenacoes = arrayOf("Ordenação Padrão", "Crescente", "Decrescente")
         MaterialAlertDialogBuilder( requireContext() )
            .setTitle("Selecione ordenação")
            .setItems( ordenacoes ){ _, posicao ->
               val ordenacaoSelecionada = ordenacoes[posicao]
               if ( posicao == 0 ) {
                  binding.chipFiltroOrdenacao.text = "Ordenação"
               }else{
                  binding.chipFiltroOrdenacao.text = ordenacaoSelecionada
               }
            }.show()
      }

   }

   private fun inicializarRecyclerviewUltimasLojas() {
      with( binding ){
         val orientacao = RecyclerView.HORIZONTAL
         ultimasLojasAdapter = LojasAdapter(orientacao){ loja ->
            val navController = findNavController()
            navController.navigate(R.id.action_homeFragment_to_lojaFragment)
         }
         rvUltimasLojas.adapter = ultimasLojasAdapter
         rvUltimasLojas.layoutManager = LinearLayoutManager(
            context, orientacao, false
         )
      }
   }

   private fun inicializarRecyclerviewLojas() {
      with( binding ){
         val orientacao = RecyclerView.VERTICAL
         lojasAdapter = LojasAdapter(orientacao){ loja ->
            val navController = findNavController()
            navController.navigate(R.id.action_homeFragment_to_lojaFragment)
         }
         rvLojas.adapter = lojasAdapter
         rvLojas.layoutManager = LinearLayoutManager(
            context, orientacao, false
         )
      }
   }

   private fun inicializarSlider() {

      val tipoEscalaImagem = ScaleTypes.CENTER_CROP
      val listaSlides = ArrayList<SlideModel>()
      listaSlides.add( SlideModel(
         "https://static.ifood-static.com.br/image/upload/t_high/discoveries/1908SMHOUSE12788PromotionsEntregaGratiscapaprincipal_VvdQ.png?imwidth=1080",
         //title = "Título para o slide",
         //scaleType = tipoEscalaImagem
      ) )
      listaSlides.add( SlideModel(
         "https://static.ifood-static.com.br/image/upload/t_high/discoveries/Banner_cl20_22_10.png?imwidth=1080",
      ) )
      listaSlides.add( SlideModel(
         "https://static.ifood-static.com.br/image/upload/t_high/discoveries/CapaPrincipalGenericoPedeiFoodJaRoxo_kpHr.png?imwidth=1080",
      ) )

      binding.imageSlider.setImageList( listaSlides, tipoEscalaImagem )

      /* Modelos de SlideAnimation
      //binding.imageSlider.setSlideAnimation( AnimationTypes.FLIP_HORIZONTAL )//1º-> FLIP_HORIZONTAL | 2º-> TOSS | 3º-> FIDGET_SPINNER
      binding.imageSlider.setItemClickListener(object : ItemClickListener{
         override fun doubleClick(position: Int) {

         }
         override fun onItemSelected(position: Int) {
            Toast.makeText(context, "posição: ${position}", Toast.LENGTH_SHORT).show()
         }
      })*/

   }

   private fun inicializarFiltrosCategoria() {
      with( binding ){
         val filtroCategoriaAdapter = FiltroCategoriaAdapter()
         rvFiltroCategoria.adapter = filtroCategoriaAdapter
         rvFiltroCategoria.layoutManager = GridLayoutManager(
            context, 4
         )
         filtroCategoriaAdapter.adicionarItens( listaFiltrosCategoria )
      }
   }

   private fun inicializarNotificacoes() {

      val menuItem = binding.tbHome.menu.findItem( R.id.item_notificacao )
      val textTotalNotificacoes = menuItem
         .actionView?.findViewById<TextView>(R.id.textTotalNotificacoes)

      textTotalNotificacoes?.text = "6"
   }

   /*private fun inicializarComponentesInterfaceCategorias() {

      with( binding ){
         categoriaAdapter = CategoriaAdapter()
         rvFiltroCategoria.adapter = categoriaAdapter
         rvFiltroCategoria.layoutManager = GridLayoutManager(
            context,4
         )
         categoriaAdapter.adicionarLista(
            listOf(
               CategoriaDTO(R.drawable.restaurante,"Restaurante"),
               CategoriaDTO(R.drawable.mercado,"Mercado"),
               CategoriaDTO(R.drawable.farmacia,"Farmacia"),
               CategoriaDTO(R.drawable.pet,"Pet"),
               CategoriaDTO(R.drawable.expresso,"Expresso"),
               CategoriaDTO(R.drawable.bebida,"Bebidas"),
               CategoriaDTO(R.drawable.shopping,"Shopping"),
               CategoriaDTO(R.drawable.gourmet,"Gourmet"),
            )
         )
      }

   }*/

}