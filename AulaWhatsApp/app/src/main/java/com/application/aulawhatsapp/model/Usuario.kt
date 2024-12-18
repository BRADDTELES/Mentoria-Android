package com.application.aulawhatsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    var id: String = "",
    var nome: String = null.toString(),
    var email: String = "",
    var foto: String = ""
) : Parcelable
