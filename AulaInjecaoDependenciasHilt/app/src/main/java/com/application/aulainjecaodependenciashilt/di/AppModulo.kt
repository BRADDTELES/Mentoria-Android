package com.application.aulainjecaodependenciashilt.di

import android.content.Context
import com.application.aulainjecaodependenciashilt.classes.Bateria
import com.application.aulainjecaodependenciashilt.classes.Carro
import com.application.aulainjecaodependenciashilt.classes.Instrumento
import com.application.aulainjecaodependenciashilt.classes.Motor
import com.application.aulainjecaodependenciashilt.classes.MotorEnergiaSolar
import com.application.aulainjecaodependenciashilt.classes.MotorGasolina
import com.application.aulainjecaodependenciashilt.classes.Musico
import com.application.aulainjecaodependenciashilt.classes.Violao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class )
object AppModulo {

    @Provides
    fun provideMusico( @Named("bateria") instrumento: Instrumento ) : Musico {
        return Musico( instrumento )
    }

    @Provides
    @Named("violao")
    fun provideViolao() : Instrumento {
        return Violao()
    }

    @Provides
    @Named("bateria")
    fun provideBateria() : Instrumento {
        return Bateria()
    }

    @Provides
    fun proverCarro( motor: Motor, @ApplicationContext context: Context ) : Carro {//Veiculo
        return Carro( motor, context )
    }

    @Provides
    fun proverMotor() : Motor {
        //return MotorGasolina()
        return MotorEnergiaSolar()
    }

}