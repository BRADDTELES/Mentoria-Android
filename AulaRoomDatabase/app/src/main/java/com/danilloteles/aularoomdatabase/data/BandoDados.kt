package com.danilloteles.aularoomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.danilloteles.aularoomdatabase.data.dao.UsuarioDAO
import com.danilloteles.aularoomdatabase.data.model.Conversor
import com.danilloteles.aularoomdatabase.data.model.Usuario

@Database(
    entities = [Usuario::class],
    version = 1
)
@TypeConverters(Conversor::class)
abstract class BandoDados : RoomDatabase() {

    abstract fun recuperarUsuarioDao() : UsuarioDAO

    companion object {
        fun recuperarInstanciaRoom( context: Context ) : BandoDados {//getInstance
            return Room.databaseBuilder(
                context,
                BandoDados::class.java,
                "projeto.db"
            ).addTypeConverter( Conversor() ).build()
        }
    }

}