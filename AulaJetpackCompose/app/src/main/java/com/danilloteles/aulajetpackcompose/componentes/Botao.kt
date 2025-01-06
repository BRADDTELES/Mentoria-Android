package com.danilloteles.aulajetpackcompose.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

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

@Preview
@Composable
fun BotaoPreview (){
   Botao(texto = "Jamilton") {
      println("executou")
   }
}