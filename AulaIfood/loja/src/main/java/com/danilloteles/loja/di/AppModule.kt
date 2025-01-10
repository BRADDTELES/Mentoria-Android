package com.danilloteles.loja.di

import com.danilloteles.loja.data.remote.firebase.repository.AutenticacaoRepositoryImpl
import com.danilloteles.loja.data.remote.firebase.repository.IAutenticacaoRepository
import com.danilloteles.loja.domain.usecase.AutenticacaoUseCase
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