package com.application.aulainjecaodependenciashilt.di

import com.application.aulainjecaodependenciashilt.classes.Banda
import com.application.aulainjecaodependenciashilt.classes.Instrumento
import com.application.aulainjecaodependenciashilt.classes.Musico
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn( ActivityComponent::class )
object AppModuloInstrumento {

    @Provides
    fun provideBanda(
        @Named("violao") violao: Instrumento ,
        @Named("bateria") bateria: Instrumento
    ) : Banda {
        return Banda(violao, bateria)
    }

}