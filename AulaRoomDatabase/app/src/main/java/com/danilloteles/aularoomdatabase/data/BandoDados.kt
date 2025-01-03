package com.danilloteles.aularoomdatabase.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import com.danilloteles.aularoomdatabase.data.dao.UsuarioDAO
import com.danilloteles.aularoomdatabase.data.model.Conversor
import com.danilloteles.aularoomdatabase.data.model.Usuario

@Database(
    entities = [Usuario::class],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = BandoDados.Migration2To3::class),
    ]
)
@TypeConverters(Conversor::class)
abstract class BandoDados : RoomDatabase() {

    abstract fun recuperarUsuarioDao() : UsuarioDAO

    //@RenameTable(fromTableName = "usuarios", toTableName = "usuarios_app")
    @RenameColumn(tableName = "usuarios", fromColumnName = "sexo", toColumnName = "sexo_usuario")
    class Migration2To3 : AutoMigrationSpec

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