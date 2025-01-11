package com.danilloteles.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun View.esconderTeclado(){
   val inputMethodManager = context.getSystemService(
      Context.INPUT_METHOD_SERVICE
   ) as InputMethodManager

   inputMethodManager.hideSoftInputFromWindow(//Esconder o Teclado
      windowToken, 0
   )
   //inputMethodManager.showSoftInput(this, 0)
}

fun Activity.exibirMensagem( texto: String ){
   Toast.makeText(
      this,
      texto,
      Toast.LENGTH_SHORT
   ).show()
}

fun <T>Activity.navegarPara( destino: Class<T>, finalizar: Boolean = true ) {
   startActivity(
      Intent(this, destino)
   )
   if(finalizar) finish()
}