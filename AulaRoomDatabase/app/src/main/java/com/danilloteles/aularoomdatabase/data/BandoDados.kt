package com.danilloteles.aularoomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.danilloteles.aularoomdatabase.data.dao.UsuarioDAO
import com.danilloteles.aularoomdatabase.data.model.Usuario

@Database(
    entities = [Usuario::class],
    version = 1
)
abstract class BandoDados : RoomDatabase() {

    abstract fun recuperarUsuarioDao() : UsuarioDAO

    companion object {
        fun recuperarInstanciaRoom( context: Context ) : BandoDados {//getInstance
            return Room.databaseBuilder(
                context,
                BandoDados::class.java,
                "projeto.db"
            ).build()
        }
    }

}