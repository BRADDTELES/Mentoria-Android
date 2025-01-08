package com.danilloteles.aulaifood.di

import com.danilloteles.aulaifood.data.remote.firebase.repository.AutenticacaoRepositoryImpl
import com.danilloteles.aulaifood.data.remote.firebase.repository.IAutenticacaoRepository
import com.danilloteles.aulaifood.domain.usecase.AutenticacaoUseCase
import com.google.firebase.auth.FirebaseAuth
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

   @Provides
   fun provideAutenticaRepository(
      firebaseAuth: FirebaseAuth
   ) : IAutenticacaoRepository {
      return AutenticacaoRepositoryImpl( firebaseAuth )
   }

   @Provides
   fun provideFirebaseAuth() : FirebaseAuth {
      return FirebaseAuth.getInstance()
   }

}