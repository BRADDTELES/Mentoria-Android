package com.danilloteles.aulaifood.di

import com.danilloteles.aulaifood.data.remote.firebase.repository.UploadRepository
import com.danilloteles.aulaifood.data.remote.firebase.repository.autenticacao.AutenticacaoRepositoryImpl
import com.danilloteles.aulaifood.data.remote.firebase.repository.autenticacao.IAutenticacaoRepository
import com.danilloteles.aulaifood.data.remote.firebase.repository.loja.ILojaRepository
import com.danilloteles.aulaifood.data.remote.firebase.repository.loja.LojaRepositoryImpl
import com.danilloteles.aulaifood.data.remote.firebase.repository.produto.IProdutoRepository
import com.danilloteles.aulaifood.data.remote.firebase.repository.produto.ProdutoRepositoryImpl
import com.danilloteles.aulaifood.domain.usecase.AutenticacaoUseCase
import com.danilloteles.aulaifood.domain.usecase.produto.RecuperarProdutosPorTipoUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
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
      firebaseAuth: FirebaseAuth,
      firebaseFirestore: FirebaseFirestore
   ) : IAutenticacaoRepository {
      return AutenticacaoRepositoryImpl( firebaseAuth, firebaseFirestore )
   }

   @Provides
   fun provideLojaRepository(
      firebaseFirestore: FirebaseFirestore
   ): ILojaRepository {
      return LojaRepositoryImpl( firebaseFirestore )
   }

   @Provides
   fun provideProdutoRepository(
      firebaseFirestore: FirebaseFirestore
   ): IProdutoRepository {
      return ProdutoRepositoryImpl( firebaseFirestore )
   }

   fun provideUploadRepository(
      firebaseStorage: FirebaseStorage,
      firebaseAuth: FirebaseAuth
   ): UploadRepository {
      return UploadRepository( firebaseStorage, firebaseAuth )
   }

   @Provides
   fun provideRecuperarProdutosPorTipoUseCase(
      produtoRepositoryImpl: IProdutoRepository
   ): RecuperarProdutosPorTipoUseCase {
      return RecuperarProdutosPorTipoUseCase( produtoRepositoryImpl )
   }

   @Provides
   fun provideFirebaseAuth() : FirebaseAuth {
      return FirebaseAuth.getInstance()
   }

   @Provides
   fun provideFirebaseStorage(): FirebaseStorage {
      return FirebaseStorage.getInstance()
   }

   @Provides
   fun provideFirebaseFirestores(): FirebaseFirestore {
      return FirebaseFirestore.getInstance()
   }

}