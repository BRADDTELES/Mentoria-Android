package com.application.aularealm.database

import android.util.Log
import com.application.aularealm.model.Usuario
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class DatabaseRealm {

    /*private val configuracao = RealmConfiguration.create(schema = setOf(Usuario::class))
    val realm = Realm.open(configuracao)*/
    private val configuracao = RealmConfiguration.Builder(
        schema = setOf( Usuario::class )
    )
    private val realm = Realm.open( configuracao.build() )

    fun salvar( usuario: Usuario ){
        realm.writeBlocking {
            copyToRealm( usuario )
            Log.i("info_realm", "Dado Salvo com sucesso")
        }
    }

}