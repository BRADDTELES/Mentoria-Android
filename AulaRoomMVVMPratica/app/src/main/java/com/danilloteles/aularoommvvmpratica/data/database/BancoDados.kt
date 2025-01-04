package com.danilloteles.aularoommvvmpratica.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danilloteles.aularoommvvmpratica.data.dao.AnotacaoDAO
import com.danilloteles.aularoommvvmpratica.data.dao.CategoriaDAO
import com.danilloteles.aularoommvvmpratica.data.entity.Anotacao
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria
import com.danilloteles.aularoommvvmpratica.helper.Constantes

@Database(
    entities = [
        Categoria::class, Anotacao::class
    ],
    version = 1
)
abstract class BancoDados : RoomDatabase() {

    //Daos
    abstract val categoriaDAO: CategoriaDAO
    abstract val anotacaoDAO: AnotacaoDAO

    companion object {
        fun getInstance( context: Context ) : BancoDados {
            return Room.databaseBuilder(
                context,
                BancoDados::class.java,
                Constantes.NOME_BANCO_DADOS
            ).build()
        }
    }
}