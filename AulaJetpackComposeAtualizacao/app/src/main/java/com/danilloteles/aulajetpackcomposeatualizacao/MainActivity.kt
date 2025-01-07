package com.danilloteles.aulajetpackcomposeatualizacao

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.danilloteles.aulajetpackcomposeatualizacao.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private val binding by lazy {
         ActivityMainBinding.inflate( layoutInflater )
   }

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView( binding.root )

      binding.btnAbrirDetalhes.setOnClickListener {
         startActivity(
            Intent(this, DetalhesActivity::class.java)
         )
      }

      binding.composeAreaLista.apply {
         setViewCompositionStrategy( ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed )
         setContent {
            AreaLista()
         }
      }

   }//Fim onCreate

   @Composable
   fun AreaLista() {
      Column(
         modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .border(2.dp, Color.Red),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
      ) {
         Button(onClick = {
            startActivity(
               Intent(applicationContext, DetalhesActivity::class.java)
            )
         }) {
            Text(text = "Abrir detalhes compose")
         }
      }
   }
}//Fim Activity