package com.danilloteles.resumaojetpackcomposeaula

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danilloteles.resumaojetpackcomposeaula.ui.theme.ResumaoJetpackComposeAulaTheme

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         ResumaoJetpackComposeAulaTheme {
            Home()
         }
      }
   }//Fim onCreate

   @Composable
   fun Home() {

      //Row(
      Column(
         modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .border(2.dp, Color.Red)
            .background(Color.Magenta)
            .padding(30.dp, 30.dp),
         verticalArrangement = Arrangement.Top,
         horizontalAlignment = Alignment.Start
         /* Para Row
         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceEvenly*/
      ) {

         Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
         )

         Image(
            painter = painterResource(id = R.drawable.carro),
            contentDescription = null,
            modifier = Modifier
               .width(100.dp)
               .height(100.dp)
               .border(2.dp, Color.Yellow, CircleShape)
               .clip(CircleShape),
            contentScale = ContentScale.Crop
         )

         /*Column(
            modifier = Modifier
               .width(100.dp)
               .height(100.dp)
               .background( Color.Blue, CircleShape ),
               //.background( Color.Blue, RoundedCornerShape(20.dp) ),
               //.background( Color.Blue, CutCornerShape(20.dp, 10.dp, 20.dp, 10.dp) ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
         ) {
            Text(
               text = "JM",
               fontSize = 20.sp,
            )
         }*/

         Text(
            modifier = Modifier
               .padding(top = 5.dp),
            text = "Danillo",
            fontSize = 20.sp,
         )
         Text(
            text = "Jamilton",
            fontSize = 20.sp,
         )
         Text(
            text = "Ana",
            fontSize = 20.sp,
         )
      }//Fim Column

      /*

      Row(

         ) {
            Text(
               text = "lado",
               fontSize = 20.sp,
            )
            Text(
               text = "lado",
               fontSize = 20.sp,
            )
         }

      Button(
         modifier = Modifier
            .padding(top = 45.dp),
         onClick = {
         Toast.makeText(applicationContext, "Clicado", Toast.LENGTH_SHORT).show()
      }) {
         Text(
            text = "Danillo",
            fontSize = 20.sp,
         )
      }*/
   }

   @Preview(showBackground = true)
   @Composable
   fun GreetingPreview() {
      ResumaoJetpackComposeAulaTheme {
         Home()
      }
   }

}//Fim Activity

