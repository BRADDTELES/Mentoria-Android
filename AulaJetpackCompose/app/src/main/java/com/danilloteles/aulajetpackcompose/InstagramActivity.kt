package com.danilloteles.aulajetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danilloteles.aulajetpackcompose.componentes.AreaDestaque
import com.danilloteles.aulajetpackcompose.componentes.AreaPostagem
import com.danilloteles.aulajetpackcompose.componentes.BarraInferior
import com.danilloteles.aulajetpackcompose.componentes.BarraSuperior
import com.danilloteles.aulajetpackcompose.model.Destaque
import com.danilloteles.aulajetpackcompose.model.Postagem
import com.danilloteles.aulajetpackcompose.ui.theme.AulaJetpackComposeTheme

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
      Column(
         modifier = modifier
      ) {
         //Área destaque
         AreaDestaque( listaDestaques )

         Divider()

         //Área de Postagens
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
