package com.danilloteles.aulajetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
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
import com.danilloteles.aulajetpackcompose.ui.theme.AulaJetpackComposeTheme

class InstagramActivity : ComponentActivity() {

   @OptIn(ExperimentalMaterial3Api::class)
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         AulaJetpackComposeTheme {
            Scaffold(
               topBar = {
                  TopAppBar(
                     title = {
                        Text(text = "Instagram")
                     }
                  )
               },
               bottomBar = {
                  BottomAppBar {
                     Text(text = "Bottom App Bar")
                  }
               },
               floatingActionButton = {
                  FloatingActionButton(
                     onClick = {

                     }
                  ) {
                     Icon(
                        painter = painterResource(id = R.drawable.ic_adicionar_24),
                        contentDescription = null
                     )
                  }
               },
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
         Text(text = "Olá App")
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
