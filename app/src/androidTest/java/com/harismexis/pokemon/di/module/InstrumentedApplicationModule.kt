package com.harismexis.pokemon.di.module

import android.content.Context
import com.harismexis.pokemon.application.InstrumentedMainApplication
import dagger.Module
import dagger.Provides

@Module
class InstrumentedApplicationModule {

    @Provides
    fun providesContext(application: InstrumentedMainApplication): Context {
        return application.applicationContext
    }

}