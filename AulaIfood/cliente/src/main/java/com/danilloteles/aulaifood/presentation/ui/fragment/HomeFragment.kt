package com.danilloteles.aulaifood.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.danilloteles.aulaifood.R
import com.danilloteles.aulaifood.data.remote.dto.CategoriaDTO
import com.danilloteles.aulaifood.databinding.FragmentHomeBinding
import com.danilloteles.aulaifood.domain.model.FiltroCategoria
import com.danilloteles.aulaifood.presentation.ui.adapter.CategoriaAdapter
import com.danilloteles.aulaifood.presentation.ui.adapter.FiltroCategoriaAdapter
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel

class HomeFragment : Fragment() {

   private lateinit var binding: FragmentHomeBinding
   //private lateinit var categoriaAdapter: CategoriaAdapter
   private val listaFiltrosCategoria = listOf(
      FiltroCategoria("Restaurantes", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Restaurantes3_JO1o.png?imwidth=128"),
      FiltroCategoria("Mercados", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Mercados_nlXp.png?imwidth=128"),
      FiltroCategoria("Farmácias", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Farmacia_Nosz.png?imwidth=128"),
      FiltroCategoria("Pet", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Pet_e9dU.png?imwidth=128"),
      FiltroCategoria("Bebidas", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Bebidas_x2Lk.png?imwidth=128"),
      FiltroCategoria("Shopping", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/floreseperfume_zTRv.png?imwidth=128"),
      FiltroCategoria("Gourmet", "https://static.ifood-static.com.br/image/upload/t_medium/discoveries/Gourmet_RQ4A.png?imwidth=128"),
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

      /*binding.textLoja.setOnClickListener {
         val navController = findNavController()
         navController.navigate(R.id.lojaFragment)
      }*/

      return binding.root
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