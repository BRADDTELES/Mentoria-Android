package com.danilloteles.aularoommvvmpratica.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danilloteles.aularoommvvmpratica.data.entity.Categoria
import com.danilloteles.aularoommvvmpratica.helper.Constantes

@Database(
    entities = [
        Categoria::class,
    ],
    version = 1
)
abstract class BancoDados : RoomDatabase() {

    //Daos

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