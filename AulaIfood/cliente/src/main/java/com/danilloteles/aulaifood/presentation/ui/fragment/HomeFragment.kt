package com.danilloteles.aulaifood.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danilloteles.aulaifood.R
import com.danilloteles.aulaifood.databinding.FragmentHomeBinding
import com.danilloteles.aulaifood.domain.model.FiltroCategoria
import com.danilloteles.aulaifood.domain.model.Loja
import com.danilloteles.aulaifood.presentation.ui.adapter.FiltroCategoriaAdapter
import com.danilloteles.aulaifood.presentation.ui.adapter.LojasAdapter
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

class HomeFragment : Fragment() {

   private lateinit var binding: FragmentHomeBinding
   //private lateinit var categoriaAdapter: CategoriaAdapter
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
   private val lojas = listOf(
      Loja("MC Donalds","Lanche","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/e35a1e98-0584-4315-afcb-ad6c621ce28a/202408251240_PNLZ.png?imwidth=128"),
      Loja("Burger King","Lanche","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/b66d0e6b-465e-45db-af1c-02a625072c95/202408011950_8A10.png?imwidth=128"),
      Loja("Donuts À Bessa","Doces & Bolos","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/08a030c7-45fa-4d3c-8c37-e39d2d077e28/202005212131_L7YE_i.jpg?imwidth=128"),
      Loja("Açaí Emporio","Açaí","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/a66dae6c-5466-451a-826b-70babe4c246d/202412090818_M4Pi_i.jpg?imwidth=128"),Loja("MC Donalds","Lanche","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/e35a1e98-0584-4315-afcb-ad6c621ce28a/202408251240_PNLZ.png?imwidth=128"),
      Loja("Panificadora Panini","Padaria","https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/606929b7-3b67-4116-9728-f486e3ec1d99/202003231200_HDzR_i.png"),
      Loja("QG","Brasileira","https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/3fa2e9ab-4aad-4423-89de-d1a89d4222fd/202412081334_J9fA_i.jpg"),
      Loja("Comida Caseira da Maria","Marmita","https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/b804bee2-74d4-4689-af5e-5ad863caabdb/202210171620_iypQ_i.jpg"),
      Loja("Parmeginando Delivery","Italiana","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/a1649f68-ef34-4afc-b0dd-7ec8562dfc1c/202408221249_71TG_i.jpg?imwidth=128"),
   )
   private val ultimasLojas = listOf(
      Loja("Burger King","Lanche","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/b66d0e6b-465e-45db-af1c-02a625072c95/202408011950_8A10.png?imwidth=128"),
      Loja("Açaí Emporio","Açaí","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/a66dae6c-5466-451a-826b-70babe4c246d/202412090818_M4Pi_i.jpg?imwidth=128"),Loja("MC Donalds","Lanche","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/e35a1e98-0584-4315-afcb-ad6c621ce28a/202408251240_PNLZ.png?imwidth=128"),
      Loja("Parmeginando","Italiana","https://static.ifood-static.com.br/image/upload/t_medium/logosgde/a1649f68-ef34-4afc-b0dd-7ec8562dfc1c/202408221249_71TG_i.jpg?imwidth=128"),
      Loja("Panini","Padaria","https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/606929b7-3b67-4116-9728-f486e3ec1d99/202003231200_HDzR_i.png"),
   )

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {

      binding = FragmentHomeBinding.inflate(
         inflater,
         container,
         false
      )

      inicializarNotificacoes()
      /* Visualização do meu RecyclerView
      inicializarComponentesInterfaceCategorias()*/
      inicializarFiltrosCategoria()
      inicializarSlider()
      inicializarLojas()
      inicializarUltimasLojas()

      return binding.root
   }

   private fun inicializarUltimasLojas() {
      with( binding ){
         val orientacao = RecyclerView.HORIZONTAL
         ultimasLojasAdapter = LojasAdapter(orientacao){ loja ->
            val navController = findNavController()
            navController.navigate(R.id.action_homeFragment_to_lojaFragment)
         }
         ultimasLojasAdapter.adicionarLista( ultimasLojas )
         rvUltimasLojas.adapter = ultimasLojasAdapter
         rvUltimasLojas.layoutManager = LinearLayoutManager(
            context, orientacao, false
         )
      }
   }

   private fun inicializarLojas() {
      with( binding ){
         val orientacao = RecyclerView.VERTICAL
         lojasAdapter = LojasAdapter(orientacao){ loja ->
            val navController = findNavController()
            navController.navigate(R.id.action_homeFragment_to_lojaFragment)
         }
         lojasAdapter.adicionarLista( lojas )
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
      //binding.imageSlider.setSlideAnimation( AnimationTypes.FLIP_HORIZONTAL )//1º-> FLIP_HORIZONTAL | 2º-> TOSS | 3º-> FIDGET_SPINNER
      /*binding.imageSlider.setItemClickListener(object : ItemClickListener{
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

   private fun inicializarComponentesInterfaceCategorias() {

      /*with( binding ){
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
      }*/

   }

   private fun inicializarNotificacoes() {

      val menuItem = binding.tbHome.menu.findItem( R.id.item_notificacao )
      val textTotalNotificacoes = menuItem
         .actionView?.findViewById<TextView>(R.id.textTotalNotificacoes)

      textTotalNotificacoes?.text = "6"
   }

}