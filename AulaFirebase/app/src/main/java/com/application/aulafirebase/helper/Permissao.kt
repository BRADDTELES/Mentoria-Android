package com.application.aulafirebase.helper

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Permissao {

    companion object {

        fun requisitarPermissoes(activity: Activity, permissoes: List<String>, requestCode: Int){

            //Verificar permissões negadas, para então solicitar
            val permissoesNegadas = mutableListOf<String>()
            permissoes.forEach { permissao ->
                val temPermissao = ContextCompat.checkSelfPermission(
                    activity, permissao
                ) == PackageManager.PERMISSION_GRANTED

                if ( !temPermissao )
                    permissoesNegadas.add(permissao)
            }

            //Requisitar permissões negadas pelo usuário
            if ( permissoesNegadas.isNotEmpty() ){
                ActivityCompat.requestPermissions(
                    activity, permissoesNegadas.toTypedArray(), 0
                )
            }
        }

    }

}