package com.example.jsonfeed.di.module

import android.content.Context

import com.example.jsonfeed.application.InstrumentedMainApplication

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