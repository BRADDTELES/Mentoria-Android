package com.danilloteles.projetomvvmcleanhilt.di

import com.danilloteles.projetomvvmcleanhilt.data.remote.DummyAPI
import com.danilloteles.projetomvvmcleanhilt.data.repository.UsuarioRepositoryImpl
import com.danilloteles.projetomvvmcleanhilt.domain.repository.UsuarioRepository
import com.danilloteles.projetomvvmcleanhilt.domain.usecase.GetMotoristaUseCase
import com.danilloteles.projetomvvmcleanhilt.domain.usecase.GetUsuariosUseCase
import com.danilloteles.projetomvvmcleanhilt.util.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn( ViewModelComponent::class )
object AppModulo {

    @Provides
    fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl( Constantes.BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
    }

    @Provides
    fun provideDummyAPI( retrofit: Retrofit ) : DummyAPI{
        return retrofit.create(DummyAPI::class.java)
    }

    @Provides
    fun provideUsuarioRepository( dummyAPI: DummyAPI ) : UsuarioRepository {
        return UsuarioRepositoryImpl( dummyAPI )
    }

    @Provides
    fun provideUsuarioUseCase( usuarioRepository: UsuarioRepository ) : GetUsuariosUseCase {
        return GetUsuariosUseCase( usuarioRepository )
    }

    /*@Provides
    fun provideMotoristaUseCase() : GetMotoristaUseCase {
        return GetMotoristaUseCase()
    }*/

}