package com.application.aularealm.database

import android.util.Log
import com.application.aularealm.model.Usuario
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.query.Sort
import io.realm.kotlin.types.ObjectId

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

    fun listar() : RealmResults<Usuario>{

        return realm
            //.query<Usuario>("nome == $0", "jamilton").find()
            .query<Usuario>()
            //.sort("nome", Sort.ASCENDING)
            .find()

    }

    fun remover( id: ObjectId ){

        realm.writeBlocking {
            val usuarioRemover = query<Usuario>("id == $0", id)
                .find()
                .first()

            delete( usuarioRemover )
        }

    }

    fun atualizar( usuario: Usuario ){
        realm.writeBlocking {
            val usuarioAtualizar = query<Usuario>("id == $0", usuario.id)
                .find()
                .first()

            usuarioAtualizar.nome = usuario.nome
            usuarioAtualizar.idade = usuario.idade
        }
    }

}