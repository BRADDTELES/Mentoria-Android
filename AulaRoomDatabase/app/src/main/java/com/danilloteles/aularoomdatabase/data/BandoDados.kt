package com.danilloteles.aularoomdatabase.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.DeleteTable
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.danilloteles.aularoomdatabase.data.dao.EnderecoDAO
import com.danilloteles.aularoomdatabase.data.dao.UsuarioDAO
import com.danilloteles.aularoomdatabase.data.model.Conversor
import com.danilloteles.aularoomdatabase.data.model.Endereco
import com.danilloteles.aularoomdatabase.data.model.Usuario

@Database(
    entities = [Usuario::class, Endereco::class],
    version = 6,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = BandoDados.Migration2To3::class),
        AutoMigration(from = 3, to = 4, spec = BandoDados.Migration3To4::class),
        AutoMigration(from = 4, to = 5, spec = BandoDados.Migration4To5::class),
    ]
)
@TypeConverters(Conversor::class)
abstract class BandoDados : RoomDatabase() {

    //abstract fun recuperarUsuarioDao() : UsuarioDAO //instanciando com método
    abstract val usuarioDAO: UsuarioDAO //instancioando com atributo
    abstract val enderecoDAO: EnderecoDAO //instancioando com atributo

    //@RenameTable(fromTableName = "usuarios", toTableName = "usuarios_app")
    @RenameColumn(tableName = "usuarios", fromColumnName = "sexo", toColumnName = "sexo_usuario")
    class Migration2To3 : AutoMigrationSpec

    //@DeleteTable(tableName = "usuarios")
    @DeleteColumn(tableName = "usuarios", columnName = "sexo_usuario")
    class Migration3To4 : AutoMigrationSpec

    @DeleteColumn(tableName = "usuarios", columnName = "rua")
    @DeleteColumn(tableName = "usuarios", columnName = "numero")
    class Migration4To5 : AutoMigrationSpec

    companion object {

        private val migration5To6 = object : Migration(5, 6){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS enderecos(" +
                        "id_endereco INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "rua TEXT NOT NULL" +
                ")")
            }

        }

        fun recuperarInstanciaRoom( context: Context ) : BandoDados {//getInstance
            return Room.databaseBuilder(
                context,
                BandoDados::class.java,
                "projeto.db"
            ).addMigrations( migration5To6 )
            .addTypeConverter( Conversor() )
            .build()
        }
    }

}