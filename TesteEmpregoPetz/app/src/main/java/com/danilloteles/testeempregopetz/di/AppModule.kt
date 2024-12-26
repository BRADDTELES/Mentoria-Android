package com.danilloteles.testeempregopetz.di

import com.danilloteles.testeempregopetz.data.api.BaseInterceptor
import com.danilloteles.testeempregopetz.utils.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit( okHttpClient: OkHttpClient ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl( Constantes.BASE_URAL )
            .addConverterFactory( GsonConverterFactory.create() )
            .client( okHttpClient )
            .build()
    }

    @Provides
    fun provideOkHttp() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor( BaseInterceptor() )
            .build()
    }

}