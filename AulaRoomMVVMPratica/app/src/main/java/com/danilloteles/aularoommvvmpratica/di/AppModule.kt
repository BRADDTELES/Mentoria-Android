package com.danilloteles.aularoommvvmpratica.di

import android.content.Context
import com.danilloteles.aularoommvvmpratica.data.dao.CategoriaDAO
import com.danilloteles.aularoommvvmpratica.data.database.BancoDados
import com.danilloteles.aularoommvvmpratica.data.repository.CategoriaRepository
import com.danilloteles.aularoommvvmpratica.data.repository.CategoriaRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn( SingletonComponent::class )
object AppModule {

    @Provides
    fun provideBancoDados( @ApplicationContext context: Context ) : BancoDados {
        return BancoDados.getInstance( context )
    }

    @Provides
    fun provideCategoriaDAO( bancoDados: BancoDados ) : CategoriaDAO {
        return bancoDados.categoriaDAO
    }

    @Provides
    fun provideCategoriaRepository( categoriaDao: CategoriaDAO ) : CategoriaRepository {
        return CategoriaRepositoryImpl( categoriaDao )
    }

}