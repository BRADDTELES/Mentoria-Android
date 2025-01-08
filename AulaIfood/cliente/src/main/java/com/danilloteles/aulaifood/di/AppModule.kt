package com.danilloteles.aulaifood.di

import com.danilloteles.aulaifood.domain.usecase.AutenticacaoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn( ViewModelComponent::class )
object AppModule {

   @Provides
   fun provideAutenticaUseCase() : AutenticacaoUseCase {
      return AutenticacaoUseCase()
   }

}