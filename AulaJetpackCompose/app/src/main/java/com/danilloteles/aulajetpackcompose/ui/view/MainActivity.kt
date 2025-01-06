package com.danilloteles.aulajetpackcompose.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danilloteles.aulajetpackcompose.R
import com.danilloteles.aulajetpackcompose.data.model.Usuario
import com.danilloteles.aulajetpackcompose.ui.theme.AulaJetpackComposeTheme

class MainActivity : ComponentActivity() {

   private val usuarios = listOf(
      Usuario("jamilton", 36),
      Usuario("ana", 45),
      Usuario("Maria", 35),
      Usuario("João", 22),
      Usuario("Pedro", 16),
      Usuario("Tiago", 65),
      Usuario("Joana", 70),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
      Usuario("...", 19),
   )

   private val opcoesRadio = listOf(
      "Android", "iOS", "Flutter", "React Native"
   )

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         AulaJetpackComposeTheme {
            SegundoApp()
         }//Fechamento theme
      }
   }//Fim OnCreate

   @Composable
   fun itemCartao( usuario: Usuario){
      Card(
         modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
         onClick = {
            Toast.makeText(applicationContext, "Clicado", Toast.LENGTH_SHORT).show()
         },
         /*colors = CardDefaults.cardColors(
            containerColor = Color.Magenta,
            contentColor = Color.Green
         )*/
         //shape = RoundedCornerShape(20.dp)
         /*elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
         )*/
      ) {
         Row(
            modifier = Modifier
               .padding(16.dp, 8.dp),
            verticalAlignment = Alignment.CenterVertically
         ) {

            Image(
               painter = painterResource(id = R.drawable.carro),
               contentDescription = null,
               modifier = Modifier
                  .height(60.dp)
                  .width(60.dp),
               contentScale = ContentScale.Crop,
            )
            Text(
               text = "${usuario.nome} - ${usuario.idade}",
               fontSize = 22.sp,
               modifier = Modifier
                  .padding(start = 16.dp)
            )
         }
      }
   }

   @Composable
   fun SegundoApp(){

      var contador by remember {
         mutableStateOf(0)
      }

      var nome by remember {
         mutableStateOf("")
      }

      var listaUsuarios by remember {
         mutableStateOf( listOf<Usuario>() )
      }

      var checked by remember {
         mutableStateOf( false )
      }

      var radioSelecionado by remember {
         mutableStateOf( opcoesRadio[0] )
      }

      Column(
         modifier = Modifier
            .background(Color.White)
            .padding(start = 16.dp, top = 53.dp)
            .fillMaxWidth()
            .fillMaxHeight()
      ) {

         LazyColumn {
            items( usuarios ){ usuario ->
               itemCartao(usuario = usuario)
            }
         }

         //Mais componentes de interface: FAB, CheckBox, Switch, Radio

         /*opcoesRadio.forEach { opcao ->

            Row(
               verticalAlignment = Alignment.CenterVertically
            ) {
               RadioButton(
                  selected = opcao == radioSelecionado,
                  onClick = {
                     radioSelecionado = opcao
                  })
               Text(text = opcao)
            }

         }
         Text(text = "alterado: $radioSelecionado")*/

         //Switch(
         /*Checkbox(
            checked = checked,
            onCheckedChange = { alterado ->
               checked = alterado
            }
         )*/

         //Text(text = "alterado: $checked")

         //FloatingActionButton(
         /*ExtendedFloatingActionButton(
            *//*containerColor = Color.Magenta,
            contentColor = Color.White,*//*
            onClick = {

            }
         ) {
            *//*Icon(
               painter = painterResource(id = R.drawable.ic_adicionar_24),
               contentDescription = null
            )*//*
            Text(text = "Pesquisar")
         }*/

         /*Row {
            //TextField(
            OutlinedTextField(
               value = nome,
               onValueChange = { texto ->
                  nome = texto
               },
               placeholder = {
                  Text(text = "Digite seu nome")
               }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
               //Adicionar na lista
               val usuario = Usuario(nome, 0)
               listaUsuarios = listaUsuarios + usuario
               nome = ""
            }) {
               Icon(
                  painter = painterResource(id = R.drawable.ic_adicionar_24),
                  contentDescription = null
               )
            }
         }*///Fim Row

         /*LazyColumn(
            modifier = Modifier
               .padding(top = 16.dp, bottom = 16.dp)
         ) {
            items(listaUsuarios){ usuario ->
               Text(
                  text = "+) ${usuario.nome} ",
                  modifier = Modifier.padding(8.dp)
               )
               Divider()
            }
         }*/

         //Text(text = "Contador: ${contador}")
         //Text(text = "nome: ${nome}")

      }//Fim column
   }//Fim método segundoApp

   @Composable
   fun PrimeiroApp() {

      Column(
         modifier = Modifier
            /*.width(200.dp)
            .height(200.dp)*/
            .background(Color.Gray)
            .border(2.dp, Color.Red)
            //.padding(30.dp, 10.dp)
            .padding(30.dp)
            //.padding(top = 30.dp, end = 30.dp)
            .fillMaxWidth()
            .fillMaxHeight()
      ) {

         //LazyRow(
         //LazyColumn(
         /*LazyVerticalGrid(
            //columns = GridCells.Adaptive(122.dp),
            columns = GridCells.Fixed(3),
            modifier = Modifier
               .padding(16.dp)
         )*/
         LazyHorizontalGrid(
            //rows = GridCells.Adaptive(122.dp),
            rows = GridCells.Fixed(3),
            modifier = Modifier
               .padding(16.dp)
               //.height(300.dp)
         ) {
            items( usuarios.size ){ indice ->

               val nome = usuarios[indice].nome
               val idade = usuarios[indice].idade

               Column {
                  Image(
                     painter = painterResource(id = R.drawable.carro),
                     contentDescription = null,
                     modifier = Modifier
                        .height(80.dp)
                        .width(80.dp),
                     contentScale = ContentScale.Crop,
                  )
                  Text(
                     text = "$nome"
                  )
               }

               /*Row(
                  modifier = Modifier
                     .padding(top = 16.dp, bottom = 16.dp),
                  verticalAlignment = Alignment.CenterVertically
               ) {
                  Image(
                     painter = painterResource(id = R.drawable.carro),
                     contentDescription = null,
                     modifier = Modifier
                        .height(80.dp)
                        .width(80.dp),
                     contentScale = ContentScale.Crop,
                     //alignment = Alignment.TopCenter
                  )
                  Text(
                     text = "$nome - $idade",
                     fontSize = 32.sp,
                     modifier = Modifier
                        .padding(start = 16.dp)
                  )
               }*///Fim Row

               /*Box(
                  modifier = Modifier
                     .fillMaxWidth()
                     .height(1.dp)
                     .background( Color.Red )
               )*/

            }
         }

         /*for (i in 1..4){
            Image(
               painter = painterResource(id = R.drawable.carro),
               contentDescription = null,
               modifier = Modifier
                  .height(200.dp)
                  .width(200.dp)
                  .border(2.dp, Color.Red),
               contentScale = ContentScale.Crop,
               //alignment = Alignment.TopCenter
            )
         }*/

         /*Image(
            painter = painterResource(id = R.drawable.carro),
            contentDescription = null,
            modifier = Modifier
               .height(200.dp)
               .width(200.dp)
               .border(2.dp, Color.Red),
            contentScale = ContentScale.Crop,
            //alignment = Alignment.TopCenter
         )

         Icon(
            //painter = painterResource(id = R.drawable.ic_alarme_24),
            imageVector = Icons.Default.Lock,
            contentDescription = null,
            *//*modifier = Modifier
            .height(200.dp)
            .width(200.dp)
            .border(2.dp, Color.Red)*//*
      )

      Button(onClick = {}) {
         Row(
            verticalAlignment = Alignment.CenterVertically
         ) {
            if ( true ) {
               Icon(
                  imageVector = Icons.Default.Lock,
                  contentDescription = null,
               )
            }else{
               Icon(
                  imageVector = Icons.Default.Email,
                  contentDescription = null,
               )
            }
            Text(text = "Desbloquear")
         }
      }*/

         /*Text(text = "Jamilton", fontSize = 20.sp)

         Column(
            modifier = Modifier
               .width(100.dp)
               .height(100.dp)
               //.clip( CircleShape )
               //.clip( RoundedCornerShape( topStart = 20.dp, topEnd = 8.dp, bottomEnd = 20.dp, bottomStart = 8.dp ) )
               //.clip( CutCornerShape( topStart = 20.dp, topEnd = 8.dp, bottomEnd = 20.dp, bottomStart = 8.dp ) )
               //.clip( CutCornerShape( 8.dp ) )
               .border(2.dp, Color.Red, CircleShape )
               .clip( CircleShape )
               .background(Color.Green),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
               //.padding(top = 10.dp, end = 30.dp, bottom = 30.dp, start = 8.dp)
         ) {
            Text(text = "JM", fontSize = 32.sp)
         }*/

      }//Fim Column

      /*Column (
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

      }*/ //Fim Column

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
   fun AppPreview() {
      SegundoApp()
   }

}//fechamento MainActivity

