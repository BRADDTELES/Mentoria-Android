package com.danilloteles.aulaifood.domain.model

import android.net.Uri

data class UploadStorage(
   val local: String,
   val nomeImagem: String,
   val urlImagem: Uri
)
