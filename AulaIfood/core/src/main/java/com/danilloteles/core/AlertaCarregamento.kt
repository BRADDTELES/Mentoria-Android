package com.danilloteles.core

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog

class AlertaCarregamento(
   private val context: Context
) {

   private var viewCarregamento: View? = null
   private var alertDialog: AlertDialog? = null

   fun exibir( titulo: String ) {

      viewCarregamento = View.inflate(
         context, R.layout.layout_carregamento, null
      )

      val alertBuilder = AlertDialog.Builder(context)
         .setTitle( titulo )
         .setView( viewCarregamento )
         .setCancelable(false)

      alertDialog = alertBuilder.create()
      alertDialog?.show()
   }

   fun fechar() {
      alertDialog?.dismiss()
   }
   
}