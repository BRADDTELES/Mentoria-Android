package com.danilloteles.projetomvvmcleanhilt.di

import com.danilloteles.projetomvvmcleanhilt.data.remote.DummyAPI
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

}