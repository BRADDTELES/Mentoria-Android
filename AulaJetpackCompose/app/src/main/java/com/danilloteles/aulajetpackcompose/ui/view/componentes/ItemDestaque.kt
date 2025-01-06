package com.danilloteles.aulajetpackcompose.ui.view.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.danilloteles.aulajetpackcompose.data.remote.model.Destaque

@Composable
fun ItemDestaque(
   destaque: Destaque,
   modifier: Modifier = Modifier
) {
   
   Column(
      modifier = modifier.padding(8.dp)
   ) {
      
      Image(
         modifier = Modifier
            .size(56.dp)
            .clip(CircleShape),
         painter = painterResource(id = destaque.imagemPerfilRes),
         contentDescription = null,
         contentScale = ContentScale.Crop
      )
      Text(
         text = destaque.nome,
         style = MaterialTheme.typography.titleMedium
      )
   }

}