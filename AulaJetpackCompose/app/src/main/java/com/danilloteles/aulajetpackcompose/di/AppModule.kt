package com.danilloteles.aulajetpackcompose.di

import com.danilloteles.aulajetpackcompose.data.remote.api.DummyAPI
import com.danilloteles.aulajetpackcompose.utils.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn( SingletonComponent::class )
object AppModule {

   @Provides
   fun provideRetrofit() : Retrofit {
      return Retrofit.Builder()
         .baseUrl( Constantes.BASE_URL )
         .addConverterFactory( GsonConverterFactory.create() )
         .build()
   }

   @Provides
   fun provideDummyAPI( retrofit: Retrofit ) : DummyAPI {
      return retrofit.create( DummyAPI::class.java )
   }

}