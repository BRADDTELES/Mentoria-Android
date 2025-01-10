package com.danilloteles.loja.domain.model

import android.net.Uri

data class UploadLoja(
   val local: String,
   val nomeImagem: String,
   val urlImagem: Uri
)
