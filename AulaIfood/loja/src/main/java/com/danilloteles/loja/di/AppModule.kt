package com.danilloteles.loja.di

import com.danilloteles.loja.data.remote.firebase.repository.AutenticacaoRepositoryImpl
import com.danilloteles.loja.data.remote.firebase.repository.IAutenticacaoRepository
import com.danilloteles.loja.data.remote.firebase.repository.ILojaRepository
import com.danilloteles.loja.data.remote.firebase.repository.LojaRepositoryImpl
import com.danilloteles.loja.data.remote.firebase.repository.UploadRepository
import com.danilloteles.loja.domain.usecase.AutenticacaoUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/* Se caso usar o @InstallIn( SingletonComponent::class ): preciso Ativar
* a Injeção de dependência no HomeActivity.kt,
* @Inject lateinit var firebaseAuth: FirebaseAuth
*
*  Caso a não utilização do SingletonComponent: deve utilizar o
* @InstallIn( ViewModelComponent::class )
* Desativando a Injeção de dependência no HomeActivity.kt,
* @Inject lateinit var firebaseAuth: FirebaseAuth
*/
@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

   @Provides
   fun provideAutenticaUseCase(): AutenticacaoUseCase {
      return AutenticacaoUseCase()
   }

   @Provides
   fun provideAutenticaRepository(
      firebaseAuth: FirebaseAuth,
      firebaseFirestore: FirebaseFirestore
   ): IAutenticacaoRepository {
      return AutenticacaoRepositoryImpl( firebaseAuth, firebaseFirestore )
   }

   @Provides
   fun provideUploadRepository(
      firebaseStorage: FirebaseStorage,
      firebaseAuth: FirebaseAuth
   ): UploadRepository {
      return UploadRepository( firebaseStorage, firebaseAuth )
   }

   @Provides
   fun provideLojaRepository(
      firebaseAuth: FirebaseAuth,
      firebaseFirestore: FirebaseFirestore
   ): ILojaRepository {
      return LojaRepositoryImpl( firebaseAuth, firebaseFirestore )
   }

   @Provides
   fun provideFirebaseAuth(): FirebaseAuth {
      return FirebaseAuth.getInstance()
   }

   @Provides
   fun provideFirebaseStorage(): FirebaseStorage {
      return FirebaseStorage.getInstance()
   }

   @Provides
   fun provideFirebaseFirestoree(): FirebaseFirestore {
      return FirebaseFirestore.getInstance()
   }

}