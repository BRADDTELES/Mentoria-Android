package com.danilloteles.aulajetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.danilloteles.aulajetpackcompose.componentes.Botao
import com.danilloteles.aulajetpackcompose.ui.theme.AulaJetpackComposeTheme

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         AulaJetpackComposeTheme {
            PrimeiroApp()
         }//Fechamento theme
      }
   }

}//fechamento MainActivity

@Composable
fun PrimeiroApp() {

   Column (
      modifier = Modifier
         .fillMaxWidth()
         .fillMaxHeight()
         .background(Color.Gray),
      verticalArrangement = Arrangement.SpaceAround,
      horizontalAlignment = Alignment.CenterHorizontally
      //horizontalArrangement = Arrangement.SpaceEvenly,
      //verticalAlignment = Alignment.CenterVertically
   ) {

      Text(text = "Jamilton", fontSize = 20.sp)
      Text(text = "Ana", fontSize = 20.sp)
      Botao(texto = "Jamilton") {
         println("executou")
      }

   } //Fim Column

   /*Text(
      text = "Danillo",
      color = Color.White
   )*/
   /*Button(onClick = {  }) {
      Text(
         text = "Danillo",
         color = Color.White
      )
   }*/
}

@Preview
@Composable
fun PrimeiroAppPreview() {
   PrimeiroApp()
}