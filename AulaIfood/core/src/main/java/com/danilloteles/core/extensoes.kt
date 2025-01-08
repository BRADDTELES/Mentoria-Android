package com.danilloteles.core

import android.app.Activity
import android.content.Intent
import android.widget.Toast

fun Activity.exibirMensagem( texto: String ){
   Toast.makeText(
      this,
      texto,
      Toast.LENGTH_SHORT
   ).show()
}

fun <T>Activity.navegarPara( destino: Class<T> ) {
   startActivity(
      Intent(this, destino)
   )
   finish()
}