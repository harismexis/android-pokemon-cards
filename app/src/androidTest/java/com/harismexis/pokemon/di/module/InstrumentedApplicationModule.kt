package com.harismexis.pokemon.di.module

import android.content.Context

import com.harismexis.pokemon.application.InstrumentedMainApplication
import com.harismexis.pokemon.localdb.LocalDao
import com.harismexis.pokemon.localdb.LocalDatabase

import dagger.Module
import dagger.Provides

@Module
class InstrumentedApplicationModule {

    @Provides
    fun providesContext(application: InstrumentedMainApplication): Context {
        return application.applicationContext
    }

    @Provides
    fun provideLocalDao(app: InstrumentedMainApplication): LocalDao {
        return LocalDatabase.getDatabase(app.applicationContext).localDao()
    }


}