package com.danilloteles.aulajetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
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
         .background( Color.Gray )
   ) {

      Text(
         text = "Jamilton",
         color = Color.Red,
         fontSize = 36.sp
      )

      Text(
         text = "Ana",
         color = Color.Red,
         fontSize = 36.sp
      )

   }//Fim Column

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