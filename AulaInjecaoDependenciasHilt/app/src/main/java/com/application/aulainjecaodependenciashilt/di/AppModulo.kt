package com.application.aulainjecaodependenciashilt.di

import android.content.Context
import com.application.aulainjecaodependenciashilt.classes.Carro
import com.application.aulainjecaodependenciashilt.classes.Motor
import com.application.aulainjecaodependenciashilt.classes.MotorEnergiaSolar
import com.application.aulainjecaodependenciashilt.classes.MotorGasolina
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class )
object AppModulo {

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