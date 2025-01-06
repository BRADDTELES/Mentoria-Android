package com.danilloteles.aulajetpackcompose.ui.view.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Botao (
   texto: String,
   onClick: () -> Unit
){
   Button(
      onClick = onClick,
      modifier = Modifier.fillMaxWidth()
   ) {
      Text(text = "+ ")
      Text(text = texto)
      Text(text = " +")
   }

}

/*@Composable
fun PerfilUsuario(){
   Column(
      modifier = Modifier
         .width(100.dp)
         .height(100.dp)
         .clip(CircleShape)
         .background(Color.Green),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
      //.padding(top = 10.dp, end = 30.dp, bottom = 30.dp, start = 8.dp)
   ) {
      Text(text = "JM", fontSize = 32.sp)
   }
}*/

@Preview
@Composable
fun BotaoPreview (){
   Botao(texto = "Jamilton") {
      println("executou")
   }
}