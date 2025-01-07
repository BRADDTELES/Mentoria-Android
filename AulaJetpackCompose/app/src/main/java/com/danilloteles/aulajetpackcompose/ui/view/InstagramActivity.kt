package com.danilloteles.aulajetpackcompose.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.danilloteles.aulajetpackcompose.R
import com.danilloteles.aulajetpackcompose.ui.view.componentes.AreaDestaque
import com.danilloteles.aulajetpackcompose.ui.view.componentes.AreaPostagem
import com.danilloteles.aulajetpackcompose.ui.view.componentes.BarraInferior
import com.danilloteles.aulajetpackcompose.ui.view.componentes.BarraSuperior
import com.danilloteles.aulajetpackcompose.data.remote.model.Destaque
import com.danilloteles.aulajetpackcompose.data.remote.model.Postagem
import com.danilloteles.aulajetpackcompose.ui.theme.AulaJetpackComposeTheme
import com.danilloteles.aulajetpackcompose.viewmodel.UsuarioViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstagramActivity : ComponentActivity() {

   private val listaDestaques = listOf(
      Destaque(R.drawable.perfil_01, "Jamilton"),
      Destaque(R.drawable.perfil_02, "João"),
      Destaque(R.drawable.perfil_03, "Ana"),
      Destaque(R.drawable.perfil_01, "Mario"),
      Destaque(R.drawable.perfil_02, "Pedro"),
      Destaque(R.drawable.perfil_03, "Marcela"),
      Destaque(R.drawable.perfil_01, "Jamilton"),
      Destaque(R.drawable.perfil_02, "João"),
      Destaque(R.drawable.perfil_03, "Ana"),
      Destaque(R.drawable.perfil_01, "Mario"),
      Destaque(R.drawable.perfil_02, "Pedro"),
      Destaque(R.drawable.perfil_03, "Marcela"),
      Destaque(R.drawable.perfil_01, "Jamilton"),
      Destaque(R.drawable.perfil_02, "João"),
      Destaque(R.drawable.perfil_03, "Ana"),
      Destaque(R.drawable.perfil_01, "Mario"),
      Destaque(R.drawable.perfil_02, "Pedro"),
      Destaque(R.drawable.perfil_03, "Marcela"),
   )

   private val listaPostagens = listOf(
      Postagem(R.drawable.perfil_01, "Jamilton", R.drawable.carro, "Descrição..."),
      Postagem(R.drawable.perfil_02, "João", R.drawable.praia,"Mussum Ipsum, cacilds vidis litro abertis. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis. Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis. Mauris nec dolor in eros commodo tempor. Aenean aliquam molestie leo, vitae iaculis nisl. Interagi no mé, cursus quis, vehicula ac nisi."),
      Postagem(R.drawable.perfil_03, "Ana", R.drawable.floresta,"Mussum Ipsum, cacilds vidis litro abertis. Negão é teu passadis, eu sou faxa pretis. Manduma pindureta quium dia nois paga."),
      Postagem(R.drawable.perfil_01, "Jamilton", R.drawable.carro, "Descrição..."),
      Postagem(R.drawable.perfil_02, "João", R.drawable.praia,"Mussum Ipsum, cacilds vidis litro abertis. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis. Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis. Mauris nec dolor in eros commodo tempor. Aenean aliquam molestie leo, vitae iaculis nisl. Interagi no mé, cursus quis, vehicula ac nisi."),
      Postagem(R.drawable.perfil_03, "Ana", R.drawable.floresta,"Mussum Ipsum, cacilds vidis litro abertis. Negão é teu passadis, eu sou faxa pretis. Manduma pindureta quium dia nois paga."),
      Postagem(R.drawable.perfil_01, "Jamilton", R.drawable.carro, "Descrição..."),
      Postagem(R.drawable.perfil_02, "João", R.drawable.praia,"Mussum Ipsum, cacilds vidis litro abertis. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis. Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis. Mauris nec dolor in eros commodo tempor. Aenean aliquam molestie leo, vitae iaculis nisl. Interagi no mé, cursus quis, vehicula ac nisi."),
      Postagem(R.drawable.perfil_03, "Ana", R.drawable.floresta,"Mussum Ipsum, cacilds vidis litro abertis. Negão é teu passadis, eu sou faxa pretis. Manduma pindureta quium dia nois paga."),
      Postagem(R.drawable.perfil_01, "Jamilton", R.drawable.carro, "Descrição..."),
      Postagem(R.drawable.perfil_02, "João", R.drawable.praia,"Mussum Ipsum, cacilds vidis litro abertis. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis. Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis. Mauris nec dolor in eros commodo tempor. Aenean aliquam molestie leo, vitae iaculis nisl. Interagi no mé, cursus quis, vehicula ac nisi."),
      Postagem(R.drawable.perfil_03, "Ana", R.drawable.floresta,"Mussum Ipsum, cacilds vidis litro abertis. Negão é teu passadis, eu sou faxa pretis. Manduma pindureta quium dia nois paga."),
      Postagem(R.drawable.perfil_01, "Jamilton", R.drawable.carro, "Descrição..."),
      Postagem(R.drawable.perfil_02, "João", R.drawable.praia,"Mussum Ipsum, cacilds vidis litro abertis. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis. Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis. Mauris nec dolor in eros commodo tempor. Aenean aliquam molestie leo, vitae iaculis nisl. Interagi no mé, cursus quis, vehicula ac nisi."),
      Postagem(R.drawable.perfil_03, "Ana", R.drawable.floresta,"Mussum Ipsum, cacilds vidis litro abertis. Negão é teu passadis, eu sou faxa pretis. Manduma pindureta quium dia nois paga."),
      Postagem(R.drawable.perfil_01, "Jamilton", R.drawable.carro, "Descrição..."),
      Postagem(R.drawable.perfil_02, "João", R.drawable.praia,"Mussum Ipsum, cacilds vidis litro abertis. Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis. Suco de cevadiss, é um leite divinis, qui tem lupuliz, matis, aguis e fermentis. Mauris nec dolor in eros commodo tempor. Aenean aliquam molestie leo, vitae iaculis nisl. Interagi no mé, cursus quis, vehicula ac nisi."),
      Postagem(R.drawable.perfil_03, "Ana", R.drawable.floresta,"Mussum Ipsum, cacilds vidis litro abertis. Negão é teu passadis, eu sou faxa pretis. Manduma pindureta quium dia nois paga."),
   )

   /* 1º Abordagem */
   private val usuarioViewModel: UsuarioViewModel by viewModels()
   override fun onStart() {
      super.onStart()
      usuarioViewModel.recuperarUsuarios()
   }

   @OptIn(ExperimentalMaterial3Api::class)
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         AulaJetpackComposeTheme {
            Scaffold(
               topBar = { BarraSuperior() },
               bottomBar = { BarraInferior() },
               /*floatingActionButton = {
                  FloatingActionButton(
                     onClick = {

                     }
                  ) {
                     Icon(
                        painter = painterResource(id = R.drawable.ic_adicionar_24),
                        contentDescription = null
                     )
                  }
               },*/
               //floatingActionButtonPosition = FabPosition.End
            ) { paddingInterno ->
               Home( Modifier.padding( paddingInterno) )
            }
         }
      }
   }//Fim onCreate

   @Composable
   fun Home( modifier: Modifier = Modifier ) {

      /* 2º Abordagem
      val usuarioViewModelCompose = viewModel(modelClass = UsuarioViewModel::class.java)
      usuarioViewModelCompose.recuperarUsuarios()*/

      val listaUsuarios by usuarioViewModel.usuarios.observeAsState(//precisa importar o State.getValue
         initial = emptyList()
      )

      Column(
         modifier = modifier
      ) {
         /* Área destaque */
         //AreaDestaque( listaDestaques )
         AreaDestaque( listaUsuarios )

         Divider()

         /* Área de Postagens */
         AreaPostagem(listaPostagens = listaPostagens)
      }
   }//Fim Home

   @Preview(showBackground = true)
   @Composable
   fun AppPreview() {
      AulaJetpackComposeTheme {
         Home()
      }
   }//Fim AppPreview

}//Fim Activity
