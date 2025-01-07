package com.danilloteles.resumaojetpackcomposeaula

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
      Button(
         modifier = Modifier
            .padding(top = 40.dp),
         onClick = {
         Toast.makeText(applicationContext, "Clicado", Toast.LENGTH_SHORT).show()
      }) {
         Text(
            text = "Danillo",
            fontSize = 20.sp,
         )
      }
   }

   @Preview(showBackground = true)
   @Composable
   fun GreetingPreview() {
      ResumaoJetpackComposeAulaTheme {
         Home()
      }
   }

}//Fim Activity

