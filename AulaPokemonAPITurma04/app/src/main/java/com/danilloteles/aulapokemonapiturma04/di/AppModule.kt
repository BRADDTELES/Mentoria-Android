package com.danilloteles.aulapokemonapiturma04.di

import com.danilloteles.aulapokemonapiturma04.data.remote.api.PokemonAPI
import com.danilloteles.aulapokemonapiturma04.data.remote.api.dto.PokemonResultado
import com.danilloteles.aulapokemonapiturma04.data.remote.api.repository.IPokemonRepository
import com.danilloteles.aulapokemonapiturma04.data.remote.api.repository.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn( ViewModelComponent::class )
object AppModule {

    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
    }

    @Provides
    fun providePokemonAPI(
        retrofit: Retrofit
    ) : PokemonAPI {
        return retrofit
            .create( PokemonAPI::class.java )
    }

    @Provides
    fun providePokemonRepositoryy(
        pokemonAPI: PokemonAPI
    ) : IPokemonRepository {
        return PokemonRepositoryImpl( pokemonAPI )
    }

}