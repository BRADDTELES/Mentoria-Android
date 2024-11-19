package com.application.aulainjecaodependenciashilt.di

import com.application.aulainjecaodependenciashilt.Carro
import com.application.aulainjecaodependenciashilt.Motor
import com.application.aulainjecaodependenciashilt.MotorGasolina
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class )
object AppModulo {

    @Provides
    fun proverCarro( motor: Motor ) : Carro {//Veiculo
        return Carro( motor )
    }

    @Provides
    fun proverMotor() : Motor {
        return MotorGasolina()
    }

}