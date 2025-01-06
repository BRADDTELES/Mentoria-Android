package com.danilloteles.aulajetpackcompose.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BarraInferior(
   modifier: Modifier = Modifier
){
   BottomAppBar(
      modifier = modifier,
      /*containerColor = Color.Red,
      contentColor = Color.White,*/
   ) {
      Row(
         modifier = modifier
            .fillMaxWidth(),
         horizontalArrangement = Arrangement.SpaceAround
      ) {
         IconButton(onClick = {}) {
            Icon(
               imageVector = Icons.Default.Home,
               contentDescription = null
            )
         }
         IconButton(onClick = {}) {
            Icon(
               imageVector = Icons.Default.Add,
               contentDescription = null
            )
         }
         IconButton(onClick = {}) {
            Icon(
               imageVector = Icons.Default.Search,
               contentDescription = null
            )
         }
      }
   }
}

@Preview
@Composable
fun BarraInferiorPreview(){
   BarraInferior()
}
