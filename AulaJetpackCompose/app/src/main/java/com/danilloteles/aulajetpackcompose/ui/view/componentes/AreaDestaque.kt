package com.danilloteles.aulajetpackcompose.ui.view.componentes

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.danilloteles.aulajetpackcompose.data.remote.dto.User

@Composable
fun AreaDestaque(
   //listaDestaques: List<Destaque>,
   listaUsuarios: List<User>,
   modifier: Modifier = Modifier
) {

   LazyRow(
      modifier = modifier
   ) {
      //items( listaUsuarios ){ destaque ->
         //ItemDestaque( destaque = destaque )
      items( listaUsuarios ){ usuario ->
         ItemDestaque( usuario = usuario )

      }
   }
   
}