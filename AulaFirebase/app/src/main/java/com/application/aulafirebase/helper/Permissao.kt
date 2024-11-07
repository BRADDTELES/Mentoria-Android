package com.application.aulafirebase.helper

import android.app.Activity
import androidx.core.app.ActivityCompat

class Permissao {

    companion object {

        fun requisitarPermissoes(activity: Activity, permissoes: List<String>){

            //Verificar permissões negadas, para então solicitar

            //Requisitar permissões negadas pelo usuário
            ActivityCompat.requestPermissions(
                activity, permissoes.toTypedArray(), 0
            )
        }

    }

}